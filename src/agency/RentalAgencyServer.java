package agency;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rental.ICarRentalCompany;
import rental.ReservationException;

public class RentalAgencyServer {

	private final static int LOCAL = 0;
	private final static int REMOTE = 1;

	public static void launch() throws ReservationException,
			NumberFormatException, IOException {
		// The first argument passed to the `main` method (if present)
		// indicates whether the application is run on the remote setup or not.
		int localOrRemote = LOCAL;

		RentalAgency rentalAgency = new RentalAgency();
			
		IRentalAgency stub = (IRentalAgency) UnicastRemoteObject.exportObject(rentalAgency, 0);

        // Bind the remote object's stub in the registry
        Registry registry = LocateRegistry.getRegistry();
        try {
			registry.bind("rentalAgency", stub);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
