package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import sessions.IManagerSession;
import sessions.IReservationSession;

public interface IRentalAgency extends Remote {

	public IReservationSession getReservationSession(String name) throws RemoteException;
	
	public IManagerSession getManagerSession(String name) throws RemoteException;
	
	public IReservationSession getNewReservationSession(String name) throws RemoteException;
	
	public IManagerSession getNewManagerSession(String name) throws RemoteException;

	public Object getAvailableCarTypes(Date start, Date end) throws RemoteException;

	public Quote createQuote(ReservationConstraints constraints, String clientName) throws RemoteException;

	public Reservation confirmQuote(Quote quote) throws RemoteException;

	public List<Reservation> getReservationsByRenter(String clientName) throws RemoteException;

	public int getNumberOfReservationsForCarType(String carType) throws RemoteException;
}
