package ucla.nrl.seam.test;

import ucla.nrl.seam.CodeOffloadDecider;
import ucla.nrl.seam.Location;
import ucla.nrl.seam.PhoneRuntimeLevels;

public class PhoneApplication {

	public static final String PHONE_APP = "PhoneApplication:";
	
	public final String appName;
	
	public static final String FUNCTION_ONE = "FUNCTION_ONE";
	public static final String FUNCTION_TWO = "FUNCTION_TWO";
	public static final String FUNCTION_THREE = "FUNCTION_THREE";
	public static final String FUNCTION_FOUR = "FUNCTION_FOUR";
	
	
	public enum Function{
		FUNCTION_1, FUNCTION_2, FUNCTION_3,FUNCTION_4
	}
	
	PhoneApplication(String appName){
		this.appName = appName;
	}
	
public void runFunction(Function function, String data){
	switch(function){
	case FUNCTION_1: randomFunction(data, FUNCTION_ONE,2);
	case FUNCTION_2: randomFunction(data, FUNCTION_TWO,4);
	case FUNCTION_3: randomFunction(data, FUNCTION_THREE,8);
	case FUNCTION_4: randomFunction(data, FUNCTION_FOUR,16);
	}
}

private String randomFunction(String data, String functionName, int batteryDecrease) {
	
	if(Phone.getInstance()==null){
		System.out.println(PHONE_APP+"Phone is dead");
		return "FAILURE";
	}

	PhoneRuntimeLevels phoneRuntimeLevels= Phone.getInstance().getCurrentRuntimeLevels(data.length());
	
    Location location = Phone.getInstance().getLocation();
	
	String result = "EMPTY";
	
	if(CodeOffloadDecider.getInstance().isOffloadingBeneficial(this.appName, functionName, phoneRuntimeLevels,new Integer[10], location)){
		System.out.println(PHONE_APP+"Offloading is better");
		result = CodeOffloadDecider.getInstance().offload(data);
		
		PhoneRuntimeLevels phoneEndRuntimeLevels= Phone.getInstance().getCurrentRuntimeLevels(data.length());
		CodeOffloadDecider.getInstance().notifyAppFunctionExecutionStop(this.appName, functionName, phoneEndRuntimeLevels);
	}else{
		System.out.println(PHONE_APP+"Computation on phone");
		result = performComputation(data);
		Phone.getInstance().setBatteryLevel(Phone.getInstance().getBatteryLevel()-batteryDecrease);
		
		PhoneRuntimeLevels phoneEndRuntimeLevels= Phone.getInstance().getCurrentRuntimeLevels(data.length());
		CodeOffloadDecider.getInstance().notifyAppFunctionExecutionStop(this.appName, functionName, phoneEndRuntimeLevels);
	}
	return result;
	
}

private String performComputation(String data){
	//perform some computation with the data
	return "RESULT";
}
	
}
