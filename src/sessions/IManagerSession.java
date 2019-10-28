package sessions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import rental.CarType;
import rental.ICarRentalCompany;

public interface IManagerSession extends Remote{

	public void registerCarRentalCompany(String crcName) throws RemoteException;
	
	public void unRegisterCarRentalCompany(String crcName) throws RemoteException;
	
	public Collection<ICarRentalCompany> getAllCarRentalCompanies() throws RemoteException;
	
	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company) throws RemoteException;
	
	public Set<String> getBestCustomers() throws RemoteException;
	
	public CarType getMostPopularCarType(String companyName, int year) throws RemoteException;

	public int getNumberOfReservationsByRenter(String clientName) throws RemoteException;
		
}
