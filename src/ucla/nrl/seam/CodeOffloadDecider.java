package ucla.nrl.seam;

import java.util.HashMap;
import java.util.Map;

import ucla.nrl.seam.test.Phone;

public class CodeOffloadDecider {

	public static final String DECIDER = "Decider:";
	/*Making it a singleton class*/
	private static CodeOffloadDecider codeOffloadDecider;


	public static CodeOffloadDecider getInstance(){

		if(codeOffloadDecider==null){
			initialize();
		}
		return codeOffloadDecider;
	}

	public static void initialize(){
		codeOffloadDecider = new CodeOffloadDecider(CodeOffloadDecider.Method.METHOD_1);
	}

	Method method;

	Map<String, ApplicationHistory> allApplicationHistoryMap = new HashMap<String, ApplicationHistory>();
	Map<String, PendingAppFunctionLevels> pendingApplicationHistoryMap = new HashMap<String, PendingAppFunctionLevels>();

	public CodeOffloadDecider(Method method){
		this.method = method;
	}

	public boolean isOffloadingBeneficial(String applicationName, String functionName, PhoneRuntimeLevels phoneRuntimeLevels, Location location){

		ApplicationHistory appHistory = allApplicationHistoryMap.get(applicationName);

		if(appHistory == null){

			//insert this application in the pending application list
			pendingApplicationHistoryMap.put(applicationName, new PendingAppFunctionLevels(phoneRuntimeLevels));

			//no application history available, so just have it on runtime levels;
			return isOffloadingBeneficial(phoneRuntimeLevels);
		}

		switch(this.method){
		case METHOD_1:return isOffloadingBeneficial(phoneRuntimeLevels);
		case METHOD_2:return isOffloadingBeneficial(phoneRuntimeLevels, appHistory);
		case METHOD_3:return isOffloadingBeneficial(phoneRuntimeLevels, appHistory);
		case METHOD_4:return isOffloadingBeneficial(phoneRuntimeLevels, appHistory,location);

		}
		return false;

	}

	public boolean notifyAppFunctionExecutionStop(String appName, String functionName, PhoneRuntimeLevels phoneRuntimeLevels){
		//get the pending app, and insert into app history

		PendingAppFunctionLevels currentAppPendingHistory = pendingApplicationHistoryMap.get(appName);


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

	private boolean isOffloadingBeneficial(PhoneRuntimeLevels phoneRuntimeLevels, ApplicationHistory appHistory){

		//Get predicted battery level
		//Get predicted wifi level

		//decide

		return false;

	}

	private boolean isOffloadingBeneficial(PhoneRuntimeLevels phoneRuntimeLevels, ApplicationHistory appHistory, Location location){
		return false;
	}





	public enum Method {
		METHOD_1, METHOD_2, METHOD_3, METHOD_4
	}
}
