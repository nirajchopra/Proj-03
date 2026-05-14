package in.co.rays.project_3.dto;

public class DroneDeliveryDTO extends BaseDTO {

	private String droneId;
	private String deliveryZone;
	private long payloadWeight;
	private String flightStatus;

	public String getDroneId() {
		return droneId;
	}

	public void setDroneId(String droneId) {
		this.droneId = droneId;
	}

	public String getDeliveryZone() {
		return deliveryZone;
	}

	public void setDeliveryZone(String deliveryZone) {
		this.deliveryZone = deliveryZone;
	}

	public long getPayloadWeight() {
		return payloadWeight;
	}

	public void setPayloadWeight(long payloadWeight) {
		this.payloadWeight = payloadWeight;
	}

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
