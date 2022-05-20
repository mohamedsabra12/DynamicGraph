package server;

import java.rmi.*;
import java.rmi.server.*;
import graph.IGraph;
import graph.Graph;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class Server extends UnicastRemoteObject implements IServer {
   // private ReadWriteLock rwLock;
    private IGraph graph;

    protected Server() throws RemoteException {
        super();
        graph = new Graph();
        //rwLock = new ReadWriteLock();
    }

    public synchronized List<Integer> shortestPath(List<String> patch) throws RemoteException {
        List<Integer> result = new ArrayList<>();


        for(String query: patch){


            if(query.equals("F")) {
                //runReadQueries(result, readQueries);
                break;
            }

            String [] str = query.split(" ");
            String statement = str[0];
            Integer src = Integer.parseInt(str[1]);
            Integer des = Integer.parseInt(str[2]);

          
                if(statement.equals("A")){

                    //runReadQueries(result, readQueries);
                    graph.add(src,des);
                    //System.out.println("Added " + src + " " + des);

                }else if(statement.equals("D")){
                    //runReadQueries(result, readQueries);
                    graph.remove(src,des);
                    //System.out.println("removed " + src + " " + des);
                }else if(statement.equals("Q")){
                    //readQueries.add(new int[]{src,des});
                   result.add(graph.query(src,des));
                }
        }

        return result;
    }

}
