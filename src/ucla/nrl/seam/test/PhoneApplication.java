package ucla.nrl.seam.test;

import ucla.nrl.seam.CodeOffloadDecider;
import ucla.nrl.seam.Location;
import ucla.nrl.seam.PhoneRuntimeLevels;

public class PhoneApplication {

	
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
		System.out.println("Phone is dead");
		return "FAILURE";
	}

	long currentTime = System.currentTimeMillis();
	int batteryLevel = Phone.getInstance().getBatteryLevel();
	int wifiLevel = Phone.getInstance().getBatteryLevel();
	Location location = Phone.getInstance().getLocation();
	
	PhoneRuntimeLevels phoneRuntimeLevels= new PhoneRuntimeLevels(currentTime,batteryLevel,wifiLevel,data.length());
	
	String result = "EMPTY";
	
	if(CodeOffloadDecider.getInstance().isOffloadingBeneficial(this.appName, functionName, phoneRuntimeLevels, location)){
		System.out.println("Offloading is better");
		result = CodeOffloadDecider.getInstance().offload(data);
	}else{
		System.out.println("Computation on phone");
		result = performComputation(data);
		Phone.getInstance().setBatteryLevel(batteryLevel-batteryDecrease);
	}
	return result;
	
}

private String performComputation(String data){
	//perform some computation with the data
	return "RESULT";
}
	
}
