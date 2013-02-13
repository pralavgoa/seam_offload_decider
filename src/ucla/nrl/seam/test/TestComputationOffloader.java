package ucla.nrl.seam.test;

import ucla.nrl.seam.test.PhoneApplication.Function;

public class TestComputationOffloader {

	
	public static void main(String[] args){
		Phone.startPhone();
		
		PhoneApplication app1 = new PhoneApplication("App1");
		PhoneApplication app2 = new PhoneApplication("App2");
		
		for(int i=0;i<20;i++){
			int remainder = i%4;
			
			switch(remainder){
			
			case 0:app1.runFunction(Function.FUNCTION_1, "Hello");
			case 1:app2.runFunction(Function.FUNCTION_2, "Hello");;
			case 2:app1.runFunction(Function.FUNCTION_3, "World");;
			case 3:app1.runFunction(Function.FUNCTION_4, "World");;
				
			}
		}
	}
	
}
