package rental;

import java.io.IOException;

import agency.RentalAgencyServer;

public class ServerManager {

	public static void main(String[] args) {
		try {
			DockxServer dockxServer = new DockxServer();
			dockxServer.launch(args);
			
			HertzServer hertzServer = new HertzServer();
			hertzServer.launch(args);
			
			RentalAgencyServer rentalAgencyServer = new RentalAgencyServer();
			rentalAgencyServer.launch(args);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReservationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
