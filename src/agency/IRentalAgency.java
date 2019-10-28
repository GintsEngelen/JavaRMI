package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.CarType;
import rental.ICarRentalCompany;
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

	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;

	public void addCarRentalCompany(String carRentalCompany) throws RemoteException;

	public void removeCarRentalCompany(String carRentalCompany) throws RemoteException;

	public Collection<ICarRentalCompany> getAllCarRentalCompanies() throws RemoteException;

	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company) throws RemoteException;

	public int getNumberOfReservationsByRenter(String clientName) throws RemoteException;

	public CarType getMostPopularCarType(String companyName, int year) throws RemoteException;

	public Set<String> getBestCustomers() throws RemoteException;

	public double getRentalPriceForCarTypeForCompany(String rentalCompany, String carType) throws RemoteException;
}
