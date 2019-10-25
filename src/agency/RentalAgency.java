package agency;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import sessions.IManagerSession;
import sessions.IReservationSession;
import sessions.ManagerSession;
import sessions.ReservationSession;
import sessions.Session;

public class RentalAgency implements IRentalAgency{

	//BELANGRIJK: LIFECYCLE MANAGEMENT
	
	private Map<String, ReservationSession> reservationSessions = new HashMap<>();
	private Map<String, ManagerSession> managerSessions = new HashMap<>();
	private Map<String, ICarRentalCompany> carRentalCompanies = new HashMap<>();
	
	Registry registry;
	
	public RentalAgency() {
		try {
			registry = LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public IReservationSession getReservationSession(String name) throws RemoteException {
		return (IReservationSession) UnicastRemoteObject.exportObject(reservationSessions.get(name), 0);
	}

	@Override
	public IManagerSession getManagerSession(String name) throws RemoteException {
		return (IManagerSession) UnicastRemoteObject.exportObject(managerSessions.get(name), 0);
	}

	@Override
	public IReservationSession getNewReservationSession(String name) throws RemoteException {
		try {
			ReservationSession reservationSession = 
					new ReservationSession(0, (IRentalAgency) registry.lookup("rentalAgency"), name);
			return (IReservationSession) UnicastRemoteObject.exportObject(reservationSession, 0);
		} catch (NotBoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IManagerSession getNewManagerSession(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quote createQuote(ReservationConstraints constraints, String clientName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation confirmQuote(Quote quote) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsByRenter(String clientName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfReservationsForCarType(String carType) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
