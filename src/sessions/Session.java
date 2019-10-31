package sessions;

import java.util.Set;

import agency.IRentalAgency;
import rental.CarType;

public abstract class Session {
	private int ID; 
	private IRentalAgency rentalAgency;
	public String customer;
	
	public Session(int ID, IRentalAgency rentalAgency, String customer) {
		this.ID = ID;
		this.rentalAgency = rentalAgency;
		this.customer = customer;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public IRentalAgency getRentalAgency() {
		return this.rentalAgency;
	}
		
}

