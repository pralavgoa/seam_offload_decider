package ucla.nrl.seam;

public class PhoneRuntimeLevels {

	private final long timeInMillis;
	private final int batteryLevel;
	private final int signalStrengthLevel;
	private final int dataSize;
	
	public PhoneRuntimeLevels(long timeInMillis, int batteryLevel, int signalStrengthLevel, int dataSize){
		this.timeInMillis = timeInMillis;
		this.batteryLevel = batteryLevel;
		this.signalStrengthLevel = signalStrengthLevel;
		this.dataSize = dataSize;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public int getSignalStrengthLevel() {
		return signalStrengthLevel;
	}

	public int getDataSize() {
		return dataSize;
	}

	public long getTimeInMillis() {
		return timeInMillis;
	}
	
}
