package ucla.nrl.seam;

import java.util.HashMap;
import java.util.Map;

public class ApplicationHistory {

	public static final String APP_HISTORY = "ApplicationHistory:";
	private final String applicationName;
	
	private Map<String,FunctionHistory> functionHistoryMap = new HashMap<String,FunctionHistory>();
	
	public ApplicationHistory(String applicationName){
		this.applicationName = applicationName;
	}
	
	public String getApplicationName() {
		return applicationName;
	}
	
	public boolean insertData(String functionName, FunctionRunHistory functionRunHistory){
		
		if(functionHistoryMap.get(functionName) == null){
			functionHistoryMap.put(functionName, new FunctionHistory(functionName));
		}
		functionHistoryMap.get(functionName).insertData(functionRunHistory);
		
		System.out.println(APP_HISTORY+"Inserting history "+toString());
		
		return false;
	}
	
	public String toString(){
		return applicationName+functionHistoryMap.toString();
	}

	
}
