package sessions;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import agency.IRentalAgency;
import agency.RentalAgency;
import rental.CarType;
import rental.ICarRentalCompany;

public class ManagerSession extends Session implements IManagerSession{
	
	private String customer;

	public ManagerSession(int ID, IRentalAgency rentalAgency, String customer) {
		super(ID, rentalAgency);
		this.customer = customer;
	}

	@Override
	public void registerCarRentalCompany(String crcName) throws RemoteException {
		//Register inzetten en juiste company pullen en opslaan
		super.getRentalAgency().addCarRentalCompany(carRentalCompany);
		
	}

	@Override
	public void unRegisterCarRentalCompany(String crcName carRentalCompany) throws RemoteException {
		//Register inzetten en juiste company pullen en opslaan
		super.getRentalAgency().removeCarRentalCompany(carRentalCompany);
		
	}

	@Override
	public List<ICarRentalCompany> getAllCarRentalCompanies() throws RemoteException {
		return super.getRentalAgency().getAllCarRentalCompanies();
	}

	@Override
	public int getNumberOfReservationsForCarTypeForCarRentalCompany(String carType, String company)
			throws RemoteException {
		return super.getRentalAgency().getNumberOfReservationsForCarTypeForCarRentalCompany(carType, company);
	}

	@Override
	public Set<String> getBestCustomers() throws RemoteException {
		return super.getRentalAgency().getBestCustomers();
	}

	@Override
	public CarType getMostPopularCarType(String companyName, int year) throws RemoteException {
		return super.getRentalAgency().getMostPopularCarType(companyName, year);
	}

	@Override
	public int getNumberOfReservationsByRenter(String clientName) {
		return super.getRentalAgency().getNumberOfReservationsByRenter(clientName);
	}

	

}
