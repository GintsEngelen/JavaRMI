package sessions;

import java.rmi.RemoteException;
import java.util.List;

import agency.RentalAgency;
import rental.CarType;
import rental.ICarRentalCompany;

public class ManagerSession extends Session implements IManagerSession{

	public ManagerSession(int ID, RentalAgency rentalAgency) {
		super(ID, rentalAgency);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void registerCarRentalCompany(ICarRentalCompany carRentalCompany) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unRegisterCarRentalCompany(ICarRentalCompany carRentalCompany) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ICarRentalCompany> getAllCarRentalCompanies() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getBestCustomers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarType getMostPopularCarType(String companyName, int year) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
