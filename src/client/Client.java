package client;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import server.Operation;

public class Client {
	public static void main(String[] args)  {
		try{
		   Socket socket=new Socket("Localhost",1234); 
		   System.out.println("je suis un client connecté");
		   InputStream is = socket.getInputStream(); 
		   OutputStream os = socket.getOutputStream(); 
		   ObjectOutputStream oos = new ObjectOutputStream(os);
		   Operation op= new Operation(3,3,'*');
		   oos.writeObject(op);
	       ObjectInputStream ois= new ObjectInputStream(is);
	       Operation opp  =(Operation)ois.readObject();
	       System.out.println("le resultat de "+opp.getOp1()+" "+opp.getOperation()+" "+opp.getOp2()+" est egale à "+opp.getResult() );
		   System.out.println("deconnexion");
		       socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();};
		}
}