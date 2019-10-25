package sessions;

import agency.IRentalAgency;

public abstract class Session {
	private int ID; 
	private IRentalAgency rentalAgency;
	
	public Session(int ID, IRentalAgency rentalAgency) {
		this.ID = ID;
		this.rentalAgency = rentalAgency;
	}
	
}

