package ubung_1;

import java.io.*;
import java.net.*;

public class Client {
	public static void main (String args[]) throws IOException
	{
		Socket client = new Socket("192.168.178.22", 61325);
		
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		BufferedReader keybordInput = new BufferedReader(new InputStreamReader(System.in));
		
		String userInput;
        while ((userInput = keybordInput.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
            out.flush();
        }
        client.close();
	}
}
//"141.45.153.32", 8003