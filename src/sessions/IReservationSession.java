package sessions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationException;

public interface IReservationSession extends Remote {
	
	public List<Quote> getCurrentQuotes() throws RemoteException;
	
	public List<Reservation> confirmQuotes(String name) throws RemoteException;
	
	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
	
	public String getCheapestCarType(Date start, Date end, String region) throws RemoteException;

	public void addQuote(String name, Date start, Date end, String carType, String region) throws RemoteException, ReservationException;
	
}
