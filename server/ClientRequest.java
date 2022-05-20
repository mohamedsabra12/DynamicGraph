package server;
// Java program for client application
import java.rmi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException; 

public class ClientRequest
{

    public static void main(String args[])
    {
        
    try {
         long start = System.currentTimeMillis();
         FileWriter myWriter = new FileWriter("server\\start.txt");
         myWriter.write(String.valueOf(start));
         myWriter.close();
    } catch (IOException e) {
        System.out.println("An error occurred.");
         e.printStackTrace();
    }

            for(int i=0 ; i<1; i++){
            // lookup method to find reference of remote object
                     new Thread(){

                            Random vertRand = new Random();
                            Random queryRand = new Random();
                            String [] queries = new String[]{"A","Q","D"};
                             String value="Reflection in Java";
                             List<Integer> answer;
                             public void run(){
                                   try
                                    {
                                                    
                                    IServer access =
                                            (IServer) Naming.lookup("rmi://localhost:1900"+
                                                    "/geeksforgeeks");

                                    IServer OptAccess =
                                            (IServer) Naming.lookup("rmi://localhost:1800"+
                                                    "/geeksforgeeks");


                                    List<String> list = new ArrayList<>();
                                    for(int i=0 ; i<10000; i++){
                                        int q = queryRand.nextInt(queries.length);
                                        int v1 = vertRand.nextInt(600) + 1;
                                        int v2 = vertRand.nextInt(600) + 1;
                                        String statement = queries[q] + " " + v1 + " " + v2;
                                        list.add(statement);
                                    }

                                    list.add("F");




                                    long start = System.currentTimeMillis();
                                    answer = access.shortestPath(list);
                                    System.out.println("Article on " + value +
                                            " " + answer+" at GeeksforGeeks");

                                    long end = System.currentTimeMillis();
                                    System.out.println("Normal Took:"  + (end - start));


                                    start = System.currentTimeMillis();
                                    answer = OptAccess.shortestPath(list);
                                    System.out.println("Article on " + value +
                                            " " + answer+" at GeeksforGeeks");
                                    end = System.currentTimeMillis();
                                    System.out.println("Opt Took:"  + (end - start));

                                }
                                 catch(Exception ae)
                                {
                                    System.out.println(ae);
                                }
                    }
                }.start();
            }

            System.out.println("Main is dead");
        }
                
    }
