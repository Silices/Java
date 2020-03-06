package ubung_1;

import java.io.*;
import java.net.*;

public class Server {
	public static void main (String args[]) throws IOException
	{
		ServerSocket server = new ServerSocket(61325);
		Socket client = server.accept();
		
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		String inputLine;
		
        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine);
            System.out.println(inputLine);
            out.flush();
        }
        server.close();
	
	
	
	}
	}
