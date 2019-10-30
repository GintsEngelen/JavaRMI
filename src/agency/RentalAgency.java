package agency;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;
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
	
	public RentalAgency() throws RemoteException {
		try {
			registry = LocateRegistry.getRegistry();
			this.carRentalCompanies.put("hertz", (ICarRentalCompany) registry.lookup("Hertz"));
			this.carRentalCompanies.put("dockx", (ICarRentalCompany) registry.lookup("Dockx"));
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public IReservationSession getReservationSession(String name) throws RemoteException {
		System.out.println("RentalAgency: getReservationSession with name <" + name + ">");
		return (IReservationSession) UnicastRemoteObject.exportObject(reservationSessions.get(name), 0);
	}

	@Override
	public IManagerSession getManagerSession(String name) throws RemoteException {
		System.out.println("RentalAgency: getManagerSession with name <" + name + ">");
		return (IManagerSession) UnicastRemoteObject.exportObject(managerSessions.get(name), 0);
	}

	@Override
	public IReservationSession getNewReservationSession(String name) throws RemoteException {
		System.out.println("RentalAgency: getNewReservationSession with name <" + name + ">");
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
		System.out.println("RentalAgency: getNewManagerSession with name <" + name + ">");
		try {
			ManagerSession reservationSession = 
					new ManagerSession(0, (IRentalAgency) registry.lookup("rentalAgency"), name);
			return (IManagerSession) UnicastRemoteObject.exportObject(reservationSession, 0);
		} catch (NotBoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<CarType> getAvailableCarTypes(Date start, Date end, String region) throws RemoteException {
		System.out.println("RentalAgency: getAvailableCartTypes from region <" +  region + " between " + start.toString() + " and " + end.toString());
		ArrayList<CarType> types = new ArrayList<>();
		for(ICarRentalCompany crc : this.carRentalCompanies.values()) {
			if(crc.operatesInRegion(region)) {
				for(CarType t : crc.getAvailableCarTypes(start, end)) {
					types.add(t);
				}
			}
		}
		return types;
	}

	@Override

	public void addCarRentalCompany(String crcName) throws RemoteException {
		System.out.println("RentalAgency: addCrC with name <" + crcName + ">");
		try {
			ICarRentalCompany carRentalCompany = (ICarRentalCompany) registry.lookup(crcName);
			this.carRentalCompanies.put(crcName, carRentalCompany);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeCarRentalCompany(String crcName) {
		System.out.println("RentalAgency: removeCarRentalCompany with name <" + crcName + ">");
		this.carRentalCompanies.remove(crcName);		
	}

	@Override
	public Collection<ICarRentalCompany> getAllCarRentalCompanies() {
		System.out.println("RentalAgency: getAllCarRentalCompanies");
		return this.carRentalCompanies.values();
	}

	@Override
	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company) throws RemoteException {
		System.out.println("RentalAgency: getNumberOfReservationsForCarTypeForCarRentalCompany with name " + company + " and type " + carType);
		return carRentalCompanies.get(company).getNumberOfReservationsForCarType(carType);
	}

	@Override
	public int getNumberOfReservationsByRenter(String clientName) throws RemoteException{
		System.out.println("RentalAgency: getNumberofReservationByRenter with name <" + clientName + ">");
		int result = 0;
		for(ICarRentalCompany carRentalCompany : getAllCarRentalCompanies()) {
			result += carRentalCompany.getNumberOfReservationsByRenter(clientName);
		}
		return result;
	}

	@Override
	public CarType getMostPopularCarType(String companyName, int year) throws RemoteException {
		System.out.println("RentalAgency: getMostPopulaCarType from company with name <" + companyName + "> with year " + year);
		return carRentalCompanies.get(companyName).getMostPopularCarType(year);
	}

	@Override
	public Set<String> getBestCustomers() {
		System.out.println("RentalAgency: getBestCustomer");
		try {
			Set<String> renters = new HashSet<>();
			for(ICarRentalCompany carRentalCompany : getAllCarRentalCompanies()) {
				renters.addAll(carRentalCompany.getAllRenters());
			}
			
			HashMap<String, Integer> reservationsPerRenter = new HashMap<>();
			
			for(String renter : renters) {
				reservationsPerRenter.put(renter, getNumberOfReservationsByRenter(renter));
			}
			
			int highestAmountOfReservations = 0;
			if(!reservationsPerRenter.values().isEmpty()) {
				highestAmountOfReservations = Collections.max(reservationsPerRenter.values());
			}
			
			Set<String> bestCustomers = new HashSet<>();
			
			for(String renter : reservationsPerRenter.keySet()) {
				if(reservationsPerRenter.get(renter) == highestAmountOfReservations) bestCustomers.add(renter);
			}
			return bestCustomers;
			
		}catch(Exception e) {
			System.out.println("Something went wrong in finding getBestCustomer");
			return null;
		}
	}

	@Override
	public double getRentalPriceForCarTypeForCompany(String rentalCompany, String carType) throws RemoteException {
		System.out.println("RentalAgency: getRentalPriceForCarTypeForCompany with name " + rentalCompany + " with type " + carType);
		return carRentalCompanies.get(rentalCompany).getPriceForCarType(carType);
	}

	@Override
	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException {
		System.out.println("RentalAgency: getAvailableCarTypes between "+start.toString()+" and " + end.toString());
		Set<CarType> availableCarTypes = new HashSet<CarType>();
		for(ICarRentalCompany carRentalCompany : getAllCarRentalCompanies()) {
			availableCarTypes.addAll(carRentalCompany.getAvailableCarTypes(start, end));
		}
		return availableCarTypes;
	}

	@Override
	public String getCheapestCarType(Date start, Date end, String region) throws RemoteException {
		System.out.println("RentalAgency: getCheapestCarType from region "+region+" between "+start.toString()+ " and "+end.toString());
		Set<CarType> cheapestCarTypes = new HashSet<CarType>();
		for(ICarRentalCompany carRentalCompany : this.getAllCarRentalCompanies()) {
			if(carRentalCompany.operatesInRegion(region)) {
				cheapestCarTypes.add(carRentalCompany.getCheapestCarType(start, end));
			} 
		}

		double cheapestPrice = Double.MAX_VALUE;
		CarType cheapestCarType = null;
		for(CarType carType : cheapestCarTypes) {
			if(carType.getRentalPricePerDay() < cheapestPrice) {
				cheapestPrice = carType.getRentalPricePerDay();
				cheapestCarType = carType;
			}
		}
		
		return cheapestCarType.getName();
	}

	@Override 
	public Quote createQuote(ReservationConstraints constraints, String client) throws RemoteException, ReservationException {
		Quote quote = null;
		for(ICarRentalCompany carRentalCompany : getAllCarRentalCompanies()) {
			try {
				return carRentalCompany.createQuote(constraints, client);
			} catch (ReservationException e) {

			} 
		}
		
		if(quote == null) throw new ReservationException("No companies have cars available for these constraints");
		return quote;
	}
}
