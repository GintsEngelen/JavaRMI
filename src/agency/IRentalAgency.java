package agency;

import java.rmi.Remote;
import java.rmi.RemoteException;
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

	public List<CarType> getAvailableCarTypes(Date start, Date end, String region) throws RemoteException;

	public void addCarRentalCompany(ICarRentalCompany carRentalCompany);

	public void removeCarRentalCompany(ICarRentalCompany carRentalCompany);

	public List<ICarRentalCompany> getAllCarRentalCompanies();

	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company);

	public int getNumberOfReservationsByRenter(String clientName);

	public CarType getMostPopularCarType(String companyName, int year);

	public Set<String> getBestCustomers();

	public double getRentalPriceForCarTypeForCompany(String rentalCompany, String carType);
}
