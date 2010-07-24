package nxt.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;

/**
 * questo thread rimane in ascolto di una connessione usb
 * quanto un client si connette la classe gestisce request e response
 * delegando la parte di business ad altre classi
 * @author marco
 *
 */
public class USBConnectionPoint extends Thread {

	public USBConnectionPoint(){
		
	}
	
	@Override
	public void run(){
		
		while (true){
			// rimani in attesa di una connessione USB
			System.out.println("wait for client");
			NXTConnection conn = USB.waitForConnection();

			// un client si e' connesso
			System.out.println("client connected");

			// preparo gli stream sui quali rimango in ascolto
			DataInputStream input = conn.openDataInputStream();
			DataOutputStream output = conn.openDataOutputStream();
			
			// preparo la connessione
			Connection connection = new Connection(input, output);
			
			try {
				// ottengo la stringa che rappresenta la richiesta come stringa
				String requestStr = connection.getStringRequest();
				// costruisco la richiesta
				Request request = Utils.getRequestFromJSON(requestStr);
				Response response = new Response();
				// delego al dispatcher
				Dispatcher dispatcher = new Dispatcher();
				dispatcher.process(request, response);
				// costruisco la risposta in JSON
				String jsonResponse = Utils.getJSONFromResponse(response);
				// spedisco la risposta
				connection.send(jsonResponse);
				// chiudo la connessione
				connection.close();
			} catch (EOFException e) {
				System.out.println("conn closed by client");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				String jsonError = Utils
						.createJSONErrorResponse(e.getMessage());
				try {
					connection.send(jsonError);
					connection.close();
				} catch (IOException e1) {
					System.out.println("cannot send res");
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				String jsonError = Utils
						.createJSONErrorResponse(e.getMessage());
				try {
					connection.send(jsonError);
					connection.close();
				} catch (IOException e1) {
					System.out.println("cannot send res");
				}
			} catch (Exception e){
				System.out.println(e.getMessage());
				try {
					connection.send(e.getMessage());
					connection.close();
				} catch (IOException e1) {
					System.out.println("cannot send res");
				}
			}
		
			
			LCD.clear();
		}
	}
	
}
