package sessions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import rental.CarType;
import rental.Quote;
import rental.Reservation;

public interface IReservationSession extends Remote {
	
	public void createQuote() throws RemoteException;
	
	public List<Quote> getCurrentQuotes() throws RemoteException;
	
	public List<Reservation> confirmQuotes(String name) throws RemoteException;
	
	public List<CarType> getAvailableCarTypes() throws RemoteException;
	
	public String getCheapestCarType(Date start, Date end, String region) throws RemoteException;

	public void addQuote(String name, Date start, Date end, String carType, String region);
	
}
