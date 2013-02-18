package ucla.nrl.seam;

import java.util.HashMap;
import java.util.Map;

import ucla.nrl.seam.test.Phone;

public class CodeOffloadDecider {

	public static final String DECIDER = "Decider:";
	/*Making it a singleton class*/
	private static CodeOffloadDecider codeOffloadDecider;

	private Method method;

	private Map<String, ApplicationHistory> allApplicationHistoryMap = new HashMap<String, ApplicationHistory>();
	private Map<String, PendingAppFunctionLevels> pendingApplicationHistoryMap = new HashMap<String, PendingAppFunctionLevels>();



	public static CodeOffloadDecider getInstance(){

		if(codeOffloadDecider==null){
			initialize();
		}
		return codeOffloadDecider;
	}

	public static void initialize(){
		codeOffloadDecider = new CodeOffloadDecider(CodeOffloadDecider.Method.METHOD_1);
	}

	public CodeOffloadDecider(Method method){
		this.method = method;
	}

	public boolean isOffloadingBeneficial(String applicationName, String functionName, PhoneRuntimeLevels phoneRuntimeLevels, Integer[] wifiHistory, Location location){

		ApplicationHistory appHistory = allApplicationHistoryMap.get(applicationName);

		if(appHistory == null){
			System.out.println(DECIDER+"App history is null");
			//insert this application in the pending application list
			pendingApplicationHistoryMap.put(applicationName, new PendingAppFunctionLevels(phoneRuntimeLevels));

			//no application history available, so just have it on runtime levels;
			return isOffloadingBeneficial(phoneRuntimeLevels);
		}

		//App history exists, do as per app history
		switch(this.method){
		case METHOD_1:return isOffloadingBeneficial(phoneRuntimeLevels);
		case METHOD_2:return isOffloadingBeneficial(phoneRuntimeLevels, appHistory, wifiHistory);
		case METHOD_3:return isOffloadingBeneficial(phoneRuntimeLevels, appHistory, wifiHistory);
		case METHOD_4:return isOffloadingBeneficial(phoneRuntimeLevels, appHistory,wifiHistory, location);

		}
		return false;

	}

	public boolean notifyAppFunctionExecutionStop(String appName, String functionName, PhoneRuntimeLevels phoneEndRuntimeLevels){
		//get the pending app, and insert into app history

		PendingAppFunctionLevels currentAppPendingHistory = pendingApplicationHistoryMap.get(appName);

		if(currentAppPendingHistory == null){
			System.out.println(DECIDER+"Something went wrong, the app is not in the history table "+appName);
			return false;
		}

		currentAppPendingHistory.setEndRuntimeLevels(phoneEndRuntimeLevels);
		
		ApplicationHistory currAppHistory = allApplicationHistoryMap.get(appName);
		if(currAppHistory==null){
			allApplicationHistoryMap.put(appName, new ApplicationHistory(appName));
		}
		

		FunctionRunHistory functionRunHistory = new FunctionRunHistory(currentAppPendingHistory.getStartRuntimeLevels(), currentAppPendingHistory.getEndRuntimeLevels());

		allApplicationHistoryMap.get(appName).insertData(functionName, functionRunHistory);

		return false;
	}

	public String offload(String data){

		int codeOffloadingBatteryCost = 1;

		Phone.getInstance().setBatteryLevel(Phone.getInstance().getBatteryLevel()-codeOffloadingBatteryCost);
		return "RESULT";
	}

	private boolean isOffloadingBeneficial(PhoneRuntimeLevels phoneRuntimeLevels){

		int currentBatteryLevel = phoneRuntimeLevels.getBatteryLevel();

		int currentWifiLevel = phoneRuntimeLevels.getSignalStrengthLevel();

		int currDataSize = phoneRuntimeLevels.getDataSize();

		//write the decision logic here
		if(currentBatteryLevel < 25){
			return false;
		}
		if(currentWifiLevel >75){
			return true;
		}

		return false;

	}

	private boolean isOffloadingBeneficial(PhoneRuntimeLevels phoneRuntimeLevels, ApplicationHistory appHistory, Integer[] wifiHistory){

		//Get predicted battery level: based on application history
		//Get predicted WiFi level: based on 15 minutes of learning data

		//decide

		return false;

	}

	private boolean isOffloadingBeneficial(PhoneRuntimeLevels phoneRuntimeLevels, ApplicationHistory appHistory, Integer[] wifiHistory, Location location){

		//Get predicted battery level: based on application history
		//Get predicted WiFi level: based on 15 minutes of learning data

		//use location information to help


		return false;
	}





	public enum Method {
		METHOD_1, METHOD_2, METHOD_3, METHOD_4
	}
}
