package org.javabrains.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="vehicle")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//used to change the name  of default column d_type
@DiscriminatorColumn(name="vehicle_type")
public class Vehicle {
	
	@Id
	@GenericGenerator(name="CustomIdGenerator",strategy="org.javabrains.util.CustomIdGenerator",parameters={
			@Parameter(name="sequence_name", value = "vehicle_seq"),@Parameter(name="sequence_per_entity_suffix", value = "vehicle_seq")})
	@GeneratedValue(generator="CustomIdGenerator")
	@Column(name="vehicle_id")
	private String vehicleId;
	
	@Column(name="vehicle_name")
	private String vehicleName;
	
	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleName="
				+ vehicleName + "]";
	}
	

}
