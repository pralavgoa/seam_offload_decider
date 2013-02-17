package ucla.nrl.seam;

import java.util.ArrayList;
import java.util.List;

public class FunctionHistory {

	private final String functionName;
	
	private List<FunctionRunHistory> functionRunHistoryList = new ArrayList<FunctionRunHistory>();
	
	public FunctionHistory(String functionName){
		this.functionName = functionName;
	}
	
	public boolean insertData(FunctionRunHistory functionRunHistory){
		
		functionRunHistoryList.add(functionRunHistory);
		
		return false;
	}

	public String getFunctionName() {
		return functionName;
	}
	
	public List<FunctionRunHistory> getFunctionRunHistoryList(){
		return new ArrayList<FunctionRunHistory>(functionRunHistoryList);
	}
	
	
}
