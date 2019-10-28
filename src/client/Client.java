package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

import agency.IRentalAgency;
import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import sessions.IManagerSession;
import sessions.IReservationSession;
import sessions.ManagerSession;
import sessions.ReservationSession;

public class Client extends AbstractTestManagement<IReservationSession, IManagerSession> {

	/********
	 * MAIN *
	 ********/

	private final static int LOCAL = 0;
	private final static int REMOTE = 1;
	
	private IRentalAgency rentalAgency;

	/**
	 * The `main` method is used to launch the client application and run the test
	 * script.
	 */
	public static void main(String[] args) throws Exception {
		// The first argument passed to the `main` method (if present)
		// indicates whether the application is run on the remote setup or not.
		int localOrRemote = (args.length == 1 && args[0].equals("REMOTE")) ? REMOTE : LOCAL;

		String rentalAgency = "rentalAgency";

		// An example reservation scenario on car rental company 'Hertz' would be...
		Client client = new Client("trips", rentalAgency, localOrRemote);
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/

	public Client(String scriptFile, String rentalAgency, int localOrRemote) {
		super(scriptFile);
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			this.rentalAgency = (IRentalAgency) registry.lookup(rentalAgency);
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * Get the (list of) best clients, i.e. clients that have highest number of
     * reservations (across all rental agencies).
     *
     * @param ms manager session
     * @return set of best clients
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected Set<String> getBestClients(IManagerSession ms) throws Exception {
		return ms.getBestCustomers();
	}

	/**
     * Find a cheapest car type that is available in the given period and region.
     *
     * @param session the session to do the request from
     * @param start start time of the period
     * @param end end time of the period
     * @param region region of interest (if null, no limitation by region)
     *
     * @return name of a cheapest car type for the given period
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected String getCheapestCarType(IReservationSession session, Date start, Date end, String region)
			throws Exception {
		return session.getCheapestCarType(start, end, region);
	}

	/**
     * Get the most popular car type in the given car rental company.
     *
     * @param ms manager session
     * @param	carRentalCompanyName The name of the car rental company.
     * @param year year in question
     * @return the most popular car type in the given car rental company
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected CarType getMostPopularCarTypeIn(IManagerSession ms, String carRentalCompanyName, int year)
			throws Exception {
		return ms.getMostPopularCarType(carRentalCompanyName, year);
	}

	/**
     * Check which car types are available in the given period and print them.
     *
     * @param session the session to do the request from
     * @param start start time of the period
     * @param end end time of the period
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected void checkForAvailableCarTypes(IReservationSession session, Date start, Date end) throws Exception {
		for(CarType carType : session.getAvailableCarTypes(start, end)) {
			System.out.println(carType.toString());
		}
	}
	
	/**
     * Add a quote for a given car type to the session.
     *
     * @param session the session to add the reservation to
     * @param name the name of the client owning the session
     * @param start start time of the reservation
     * @param end end time of the reservation
     * @param carType type of car to be reserved
     * @param region region for which the car shall be reserved
     * should be done
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected void addQuoteToSession(IReservationSession session, String name, Date start, Date end, String carType,
			String region) throws Exception {
		session.addQuote(name, start, end, carType, region);
		
	}

	/**
     * Confirm the quotes in the given session.
     *
     * @param session the session to finalize
     * @param name the name of the client owning the session
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected List<Reservation> confirmQuotes(IReservationSession session, String name) throws Exception {
		return session.confirmQuotes(name);
	}

	/**
     * Get the number of reservations made by the given renter (across whole
     * rental agency).
     *
     * @param	ms manager session
     * @param clientName name of the renter
     * @return	the number of reservations of the given client (across whole
     * rental agency)
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected int getNumberOfReservationsByRenter(IManagerSession ms, String clientName) throws Exception {
		return ms.getNumberOfReservationsByRenter(clientName);
	}

	/**
     * Get the number of reservations for a particular car type.
     *
     * @param ms manager session
     * @param carRentalName name of the rental company managed by this session
     * @param carType name of the car type
     * @return number of reservations for this car type
     *
     * @throws Exception if things go wrong, throw exception
     */
	@Override
	protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType)
			throws Exception {
		return ms.getNumberOfReservationsForCarTypeForCarRentalCompany(carType, carRentalName);
	}

	@Override
	protected IReservationSession getNewReservationSession(String name) throws Exception {
		return this.rentalAgency.getNewReservationSession(name);
	}

	@Override
	protected IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		return this.rentalAgency.getNewManagerSession(name);
	}
}