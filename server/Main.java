package server;
// Java program for server application
import java.rmi.*;
import java.rmi.registry.*;
public class Main {

        public static void main(String args[])
        {
            try
            {
                // Create an object of the interface
                // implementation class
                IServer obj = new Server();

                // rmiregistry within the server JVM with
                // port number 1900
                LocateRegistry.createRegistry(1900);

                // Binds the remote object by the name
                // geeksforgeeks
                Naming.rebind("rmi://localhost:1900"+
                        "/geeksforgeeks",obj);
                System.out.println("done done");        
            }
            catch(Exception ae)
            {
                System.out.println(ae);
            }
        }
    }

