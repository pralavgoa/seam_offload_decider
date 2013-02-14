package ucla.nrl.seam;

public class PendingAppFunctionLevels {
	
	private final PhoneRuntimeLevels startRuntimeLevels;
	private PhoneRuntimeLevels endRuntimeLevels;
	
	public PendingAppFunctionLevels(PhoneRuntimeLevels startRuntimeLevels){
		this.startRuntimeLevels = startRuntimeLevels;
	}

	public PhoneRuntimeLevels getStartRuntimeLevels() {
		return startRuntimeLevels;
	}

	public PhoneRuntimeLevels getEndRuntimeLevels() {
		return endRuntimeLevels;
	}

	public void setEndRuntimeLevels(PhoneRuntimeLevels endRuntimeLevels) {
		this.endRuntimeLevels = endRuntimeLevels;
	}
}
