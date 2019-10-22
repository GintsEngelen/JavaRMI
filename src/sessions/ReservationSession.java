package sessions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import agency.RentalAgency;
import rental.CarType;
import rental.Quote;

public class ReservationSession extends Session implements IReservationSession{

	private List<Quote> quotes;
	private String customer;
	
	public ReservationSession(int ID, RentalAgency rentalAgency, String customer) {
		super(ID, rentalAgency);
		this.quotes = new ArrayList<Quote>();
		this.customer = customer;
	}

	@Override
	public void createQuote() throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Quote> getCurrentQuotes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmQuotes() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CarType> getAvailableCarTypes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarType getCheapestCarType() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
