package sessions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import rental.CarType;
import rental.ICarRentalCompany;

public interface IManagerSession extends Remote{

	public void registerCarRentalCompany(ICarRentalCompany carRentalCompany) throws RemoteException;
	
	public void unRegisterCarRentalCompany(ICarRentalCompany carRentalCompany) throws RemoteException;
	
	public List<ICarRentalCompany> getAllCarRentalCompanies() throws RemoteException;
	
	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company) throws RemoteException;
	
	public Set<String> getBestCustomers() throws RemoteException;
	
	public CarType getMostPopularCarType(String companyName, int year) throws RemoteException;
		
}
