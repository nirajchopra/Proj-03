package in.co.rays.project_3.dto;
import java.util.Date;

public class VehicleDTO extends BaseDTO {
	
	private String vehicleNumber ;
	private String ownerName;
	private String serviceType;
	private Date serviceDate;
	private String mechanicName;
	private String serviceCost;
	private Date nextServiceDate;


	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getMechanicName() {
		return mechanicName;
	}

	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	public Date getNextServiceDate() {
		return nextServiceDate;
	}

	public void setNextServiceDate(Date nextServiceDate) {
		this.nextServiceDate = nextServiceDate;
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

	@Override
	public String toString() {
		return "VehicleDTO [vehicleNumber=" + vehicleNumber + ", ownerName=" + ownerName + ", serviceType="
				+ serviceType + ", serviceDate=" + serviceDate + ", mechanicName=" + mechanicName + ", serviceCost="
				+ serviceCost + ", nextServiceDate=" + nextServiceDate + "]";
	}
	

}
