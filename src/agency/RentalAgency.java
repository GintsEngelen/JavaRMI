package agency;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		try {
			ManagerSession reservationSession = 
					new ManagerSession(0, (IRentalAgency) registry.lookup("rentalAgency"), name);
			return (IManagerSession) UnicastRemoteObject.exportObject(reservationSession, 0);
		} catch (NotBoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CarType> getAvailableCarTypes(Date start, Date end, String region) throws RemoteException {
		//TODO: WAAR KOMT DIE REGION VANDAAAAAAANNNN
		ArrayList<CarType> types = new ArrayList<>();
		for(ICarRentalCompany crc : this.carRentalCompanies.values()) {
			for(CarType t : crc.getAvailableCarTypes(start, end)) {
				types.add(t);
			}
		}
		return types;
	}

	@Override
	public void addCarRentalCompany(ICarRentalCompany carRentalCompany) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCarRentalCompany(ICarRentalCompany carRentalCompany) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ICarRentalCompany> getAllCarRentalCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfReservationsByRenter(String clientName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CarType getMostPopularCarType(String companyName, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getBestCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getRentalPriceForCarTypeForCompany(String rentalCompany, String carType) {
		// TODO Auto-generated method stub
		return 0;
	}



	

}
