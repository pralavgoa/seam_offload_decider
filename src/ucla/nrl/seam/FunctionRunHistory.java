package ucla.nrl.seam;

public class FunctionRunHistory {
	
	
	private final double timeDuration;
	private final double batteryConsumed;
	private final double averageWifiStrength;
	
	public FunctionRunHistory(PhoneRuntimeLevels startLevels, PhoneRuntimeLevels endLevels){
		batteryConsumed = startLevels.getBatteryLevel() - endLevels.getBatteryLevel();
		averageWifiStrength = (startLevels.getSignalStrengthLevel() + endLevels.getSignalStrengthLevel())/2;
		timeDuration = endLevels.getTimeInMillis() - startLevels.getTimeInMillis();
	}

	public double getBatteryConsumed() {
		return batteryConsumed;
	}

	public double getAverageWifiStrength() {
		return averageWifiStrength;
	}

	public double getTimeDuration() {
		return timeDuration;
	}
	
	public String toString(){
		return "TimeDuration:"+timeDuration+",BatteryConsumed:"+batteryConsumed+",AvgWifiStrength:"+averageWifiStrength;
	}
	
}
