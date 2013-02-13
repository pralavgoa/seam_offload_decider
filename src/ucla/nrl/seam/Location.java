package ucla.nrl.seam;

public class Location {

	private double latitude;
	private double longitude;
	
	private String locationName;
	
	public Location(int latitude, int longitude){
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
	public Location(int latitude, int longitude, String locationName){
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setLocationName(locationName);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
}
