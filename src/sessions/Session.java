package sessions;

import agency.RentalAgency;

public abstract class Session {
	private int ID; 
	private RentalAgency rentalAgency;
	
	public Session(int ID, RentalAgency rentalAgency) {
		this.ID = ID;
		this.rentalAgency = rentalAgency;
	}
	
}

