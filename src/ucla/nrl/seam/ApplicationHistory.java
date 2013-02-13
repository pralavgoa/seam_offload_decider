package ucla.nrl.seam;

import java.util.HashMap;
import java.util.Map;

public class ApplicationHistory {

	private final String applicationName;
	
	private Map<String,FunctionHistory> functionHistoryMap = new HashMap<String,FunctionHistory>();
	
	public ApplicationHistory(String applicationName){
		this.applicationName = applicationName;
	}
	
	public String getApplicationName() {
		return applicationName;
	}
	
	public boolean insertData(){
		return false;
	}

	
}