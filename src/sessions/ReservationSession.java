package sessions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import agency.IRentalAgency;
import agency.RentalAgency;
import rental.CarType;
import rental.Quote;
import rental.Reservation;

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
	public List<CarType> getAvailableCarTypes(Date start, Date end, String region) throws RemoteException {
		return super.getRentalAgency().getAvailableCarTypes(start, end, region);
	}

	@Override
	public String getCheapestCarType(Date start, Date end, String region) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuote(String name, Date start, Date end, String carType, String region) {
		String rentalCompany = "Nakijken wat de regels hiervoorzijn";
		double rentalPrice = super.getRentalAgency().getRentalPriceForCarTypeForCompany(rentalCompany, carType);
		Quote newQuote = new Quote(name, start, end, rentalCompany, carType, rentalPrice);
	}

	
	
	
	
}
