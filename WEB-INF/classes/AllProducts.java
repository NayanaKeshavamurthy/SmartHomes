

public class AllProducts 
{
	
	private DoorBells doorbell;
	
	private DoorLocks doorlock;
	
	private Lightings lighting;
	
	private Speakers speaker;
	
	private Thermostats thermostat;

	private String category;
	
	public DoorBells getDoorbell() 
	{
		return doorbell;
	}

	public void setDoorbell(DoorBells doorbell) {
		this.doorbell = doorbell;
	}

	public DoorLocks getDoorLock() {
		return doorlock;
	}

	public void setDoorLock(DoorLocks doorlock) {
		this.doorlock = doorlock;
	}

	public Lightings getLighting() {
		return lighting;
	}

	public void setLighting(Lightings lighting) {
		this.lighting = lighting;
	}

	public Speakers getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speakers speaker) {
		this.speaker = speaker;
	}

	public Thermostats getThermostat() {
		return thermostat;
	}

	public void setThermostat(Thermostats thermostat) {
		this.thermostat = thermostat;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
