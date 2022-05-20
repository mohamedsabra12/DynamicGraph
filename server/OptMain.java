package server;
// Java program for server application
import java.rmi.*;
import java.rmi.registry.*;
public class OptMain {

    public static void main(String args[])
    {
        try
        {
            // Create an object of the interface
            // implementation class
            IServer obj = new OptServer();

            // rmiregistry within the server JVM with
            // port number 1900
            LocateRegistry.createRegistry(1800);

            // Binds the remote object by the name
            // geeksforgeeks
            Naming.rebind("rmi://localhost:1800"+
                    "/geeksforgeeks",obj);
            System.out.println("done done");
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }
}

