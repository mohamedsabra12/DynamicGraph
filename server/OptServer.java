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



public class OptServer extends UnicastRemoteObject implements IServer {
    // private ReadWriteLock rwLock;
    private IGraph graph;
    ExecutorService exec;
    protected OptServer() throws RemoteException {
        super();
        graph = new Graph();
        exec = Executors.newFixedThreadPool(50);
    }

    public synchronized List<Integer> shortestPath(List<String> patch) throws RemoteException {


        List<Integer> result = new ArrayList<>();
        List<int[]> readQueries = new ArrayList<>();

        for(String query: patch){



            if(query.equals("F")) {
                runReadQueries(result, readQueries);
//                exec.shutdownNow();
                break;
            }

            String [] str = query.split(" ");
            String statement = str[0];
            Integer src = Integer.parseInt(str[1]);
            Integer des = Integer.parseInt(str[2]);


            if(statement.equals("A")){

                runReadQueries(result, readQueries);
                graph.add(src,des);
                //System.out.println("Added " + src + " " + des);

            }else if(statement.equals("D")){
                runReadQueries(result, readQueries);
                graph.remove(src,des);
                //System.out.println("removed " + src + " " + des);
            }else if(statement.equals("Q")){
                readQueries.add(new int[]{src,des});
                //result.add(graph.query(src,des));
            }
        }

        return result;
    }

    private void  runReadQueries(List<Integer> result, List<int[]> readQueries) {
        if(readQueries.size() == 0)
            return;


        int [] readResults = new int[readQueries.size()];

        Collection<ReaderCallable> callables = new ArrayList<>();

        for(int i = 0; i< readQueries.size() ; i++){
            int Qsrc = readQueries.get(i)[0];
            int Qdes = readQueries.get(i)[1];
            callables.add(new ReaderCallable(i,Qsrc,Qdes,graph,readResults));
        }



        try {
            exec.invokeAll((Collection)callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {

            readQueries.clear();
            for (int res : readResults)
                result.add(res);

        }
    }
}
