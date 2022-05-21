package server;
// Java program for client application

import java.rmi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;



class MyRunnable implements Runnable {
    Random vertRand = new Random();
    Random queryRand = new Random();
    String [] queries = new String[]{"A","D","Q"};

    List<Integer> answer;
    Integer threadNumber;
    Integer numberOfNodes;
    public MyRunnable(Integer threadNumber , int numberOFNodes) {
        this.threadNumber = threadNumber;
        this.numberOfNodes = numberOFNodes;
    }

    public void run(){
        try
        {

            IServer access =
                    (IServer) Naming.lookup("rmi://localhost:1900"+
                            "/geeksforgeeks");

            IServer OptAccess =
                    (IServer) Naming.lookup("rmi://localhost:1800"+
                            "/geeksforgeeks");



            int [] reqNum = {50};
            for (int n : reqNum) {
                for (int j = 0; j < n; j++) {

                    int[] frequency = new int[queries.length];

                    List<String> list = new ArrayList<>();

                    for (int i = 0; i < 100; i++) {
                        int q = queryRand.nextInt(queries.length);
                        int v1 = vertRand.nextInt(60) + 1;
                        int v2 = vertRand.nextInt(60) + 1;
                        String statement = queries[q] + " " + v1 + " " + v2;
                        frequency[q]++;
                        list.add(statement);
                    }

                    float upd = calcUpd(frequency,queries);
                    list.add("F");


                    long start = System.currentTimeMillis();
                    answer = access.shortestPath(list);
                    Logger.startExec(threadNumber, "Norm", n,upd,numberOfNodes,start);
                    System.out.println("Norm result: " + answer);



                    start = System.currentTimeMillis();
                    answer = OptAccess.shortestPath(list);
                    Logger.startExec(threadNumber, "Optm", n,upd,numberOfNodes,start);
                    System.out.println("Optm result: " + answer);

                }
            }
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }

    private float calcUpd(int[] frequency, String[] queries) {
        int sum = 0;
        int num = 0;

       for(int i=0 ; i<queries.length ; i++){
           if(!queries[i].equals("Q"))
               num += frequency[i];

           sum += frequency[i];
       }

       return num  / (float)sum;
    }
}


public class ClientRequest
{

    public static void main(String args[])
    {

        int n = 15;
        for (int i = 0; i < n; i++) {
            Runnable r = new MyRunnable(i,n);
            new Thread(r).start();
        }


            System.out.println("Main is dead");
        }
                
    }
