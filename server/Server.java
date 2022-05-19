package server;

import java.rmi.*;
import java.rmi.server.*;
import graph.IGraph;
import graph.Graph;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server extends UnicastRemoteObject implements IServer {
    private ReadWriteLock rwLock;
    private IGraph graph;
    protected Server() throws RemoteException {
        super();
        graph = new Graph();
        rwLock = new ReadWriteLock();
    }

    public  List<Integer> shortestPath(List<String> patch) throws RemoteException {
        List<Integer> result = new ArrayList<>();
        

        for(String query: patch){

            if(query.equals("F"))
                break;

            String [] str = query.split(" ");
            Integer src = Integer.parseInt(str[1]);
            Integer des = Integer.parseInt(str[2]);
            try{
                if(str[0].equals("A")){
                    rwLock.acquireWriteLock();
                    graph.add(src,des);
                    System.out.println("Added " + src + " " + des);
                    rwLock.releaseWriteLock();

                }else if(str[0].equals("D")){
                    rwLock.acquireWriteLock();
                    graph.remove(src,des);
                    System.out.println("removed " + src + " " + des);
                    rwLock.releaseWriteLock();

                }else if(str[0].equals("Q")){
                    rwLock.acquireReadLock();
                    result.add(graph.query(src,des));
                    rwLock.releaseReadLock();

                }else break;
            }catch(InterruptedException ie){
                System.out.println(ie);
            }
        }

        return result;
    }
}
