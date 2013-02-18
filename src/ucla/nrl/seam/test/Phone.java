package ucla.nrl.seam.test;

import ucla.nrl.seam.CodeOffloadDecider;
import ucla.nrl.seam.Location;
import ucla.nrl.seam.PhoneRuntimeLevels;

public class Phone {

	
	private static Phone phone;
	
	private int batteryLevel = 100;
	private int MAX_WIFI_LEVEL = 100;
	
	private Location location = new Location(0,0);
	
	public static void startPhone(){
		if(phone==null){
			phone = new Phone();
			CodeOffloadDecider.initialize();
		}
	}
	
	public static boolean stopPhone(){
		phone = null;
		return true;
	}
	
	public static Phone getInstance(){
		return phone;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		System.out.println("Phone: Battery is now at "+batteryLevel);
		if(batteryLevel<0){
			stopPhone();
			System.out.println("The phone has stopped");
			return;
		}
		this.batteryLevel = batteryLevel;
	}

	public int getWifiLevel() {
		return (int) (Math.random()*MAX_WIFI_LEVEL);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public PhoneRuntimeLevels getCurrentRuntimeLevels(int dataLength){
		long currentTime = System.currentTimeMillis();
		int batteryLevel = Phone.getInstance().getBatteryLevel();
		int wifiLevel = Phone.getInstance().getWifiLevel();
		
		return new PhoneRuntimeLevels(currentTime,batteryLevel,wifiLevel,dataLength);
	}
	
	
	
}
