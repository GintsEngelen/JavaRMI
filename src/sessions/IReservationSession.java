package sessions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import rental.CarType;
import rental.Quote;

public interface IReservationSession extends Remote {
	
	public void createQuote() throws RemoteException;
	
	public List<Quote> getCurrentQuotes() throws RemoteException;
	
	public void confirmQuotes() throws RemoteException;
	
	public List<CarType> getAvailableCarTypes() throws RemoteException;
	
	public String getCheapestCarType(Date start, Date end, String region) throws RemoteException;
	
}
