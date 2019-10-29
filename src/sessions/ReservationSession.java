package sessions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import agency.IRentalAgency;
import agency.RentalAgency;
import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

public class ReservationSession extends Session implements IReservationSession{

	private List<Quote> quotes;
	private String customer;
	
	public ReservationSession(int ID, IRentalAgency rentalAgency, String customer) {
		super(ID, rentalAgency);
		this.quotes = new ArrayList<Quote>();
		this.customer = customer;
	}
	

	@Override
	public List<Quote> getCurrentQuotes() throws RemoteException {
		return this.quotes;
	}

	@Override
	public List<Reservation> confirmQuotes(String name) throws RemoteException {
		// TODO Auto-generated method stub
		//Locking and stuff
		return null;
	}

	@Override
	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException {
		return super.getRentalAgency().getAvailableCarTypes(start, end);
	}

	@Override
	public String getCheapestCarType(Date start, Date end, String region) throws RemoteException {
		return super.getRentalAgency().getCheapestCarType(start, end, region);
	}

	@Override
	public void addQuote(String name, Date start, Date end, String carType, String region) throws RemoteException {
		ReservationConstraints constraints = new ReservationConstraints(start, end, carType, region);
		try {
			this.quotes.add(this.getRentalAgency().createQuote(constraints, name));
		} catch (ReservationException e) {
			//No quote will be added, since there is no possible quote with given constraints
		}
	}

	
	
	
	
}
