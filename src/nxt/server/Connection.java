package nxt.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Connection {
	
	private DataInputStream input;
	private DataOutputStream output;

	public Connection(DataInputStream input, DataOutputStream output) {
		this.input = input;
		this.output = output;
	}

	public String getStringRequest() throws IOException {
		StringBuffer buffer = new StringBuffer();
		char ch = input.readChar();
		while (ch != '\n'){
			buffer.append(ch);
			ch = input.readChar();
		}
		return buffer.toString();
	}

	public void send(String response) throws IOException {
		// questo sembra l'unico metodo che non mi fa perdere caratteri nella trasmissione
		char[] chars = response.toCharArray();
		for (int i=0; i<chars.length; i++){
			output.writeChar(chars[i]);
			// forzo la scrittura
			output.flush();
		}
		
	}
	
	public void close() throws IOException{
		output.close();
		input.close();
	}

}
