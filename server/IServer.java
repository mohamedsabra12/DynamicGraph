package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote{
    List<Integer> shortestPath(List<String> patch) throws RemoteException;
}
