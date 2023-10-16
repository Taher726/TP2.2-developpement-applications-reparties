package server;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args)  {
	      try {
	          ServerSocket serverSocket = new ServerSocket(1234); //on a fait une réservation du port
	          System.out.println("je suis un serveur , j'atend un client");
	          Socket socket=serverSocket.accept();
	          System.out.println("un client est connecté");
	          InputStream is = socket.getInputStream();
	          OutputStream os = socket.getOutputStream();
	          ObjectInputStream ois= new ObjectInputStream(is);//ObjectInputStream est utilisé pour désérialiser les données reçues
	          //à partir de l'inputStream
	          Operation op=(Operation)ois.readObject();//readObject() elle lu l'objet sérialisé à partir du flux d'entrée 'ois' et
	          // le renvoit de type object c pour cela on a utiliser le casting pour le convertir en type operation
	          int res;
	          switch(op.getOperation()) {
		          case '+': 
		        	  res=op.getOp1()+op.getOp2();
		        	  break;
		          case '-': 
		        	  res=op.getOp1()-op.getOp2();
		        	  break;
		          case '/': 
		        	  res=op.getOp1()/op.getOp2();
		        	  break;
		          case '*': 
		        	  res=op.getOp1()*op.getOp2();
		        	  break;
		          default : 
		        	  res = 0;
	          }
	          ObjectOutputStream ooos= new ObjectOutputStream(os);
	          op.setResult(res);
	          ooos.writeObject(op);
	          System.out.println("deconnexion");
	          socket.close();
	          serverSocket.close();
	      }
	      catch(Exception e) {e.printStackTrace();}
	}
}
