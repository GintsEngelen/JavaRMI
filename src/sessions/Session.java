package sessions;

import java.util.Set;

import agency.IRentalAgency;
import rental.CarType;

public abstract class Session {
	private int ID; 
	private IRentalAgency rentalAgency;
	
	public Session(int ID, IRentalAgency rentalAgency) {
		this.ID = ID;
		this.rentalAgency = rentalAgency;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public IRentalAgency getRentalAgency() {
		return this.rentalAgency;
	}

	
	
}

