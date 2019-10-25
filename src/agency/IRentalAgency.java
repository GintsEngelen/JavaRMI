package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import rental.CarType;
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

	public List<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
}
