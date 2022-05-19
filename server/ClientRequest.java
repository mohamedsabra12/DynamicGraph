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

            for(int i=0 ; i<5 ; i++){
            // lookup method to find reference of remote object
                     new Thread(){
                             
                             String value="Reflection in Java";
                             List<Integer> answer;
                             public void run(){
                                   try
                                    {
                                                    
                                    IServer access =
                                            (IServer) Naming.lookup("rmi://localhost:1900"+
                                                    "/geeksforgeeks");


                                    List<String> list = Arrays.asList("A 2 3" , "Q 1 3" , "D 2 3","Q 1 3","F");                
                                    answer = access.shortestPath(list);
                                    System.out.println("Article on " + value +
                                            " " + answer+" at GeeksforGeeks");

                                    try {
                                    long end = System.currentTimeMillis();
                                    System.out.println("end: " + end);

                                    
                                        long start = System.currentTimeMillis();
                                        FileWriter myWriter = new FileWriter("server\\end.txt",true);
                                        myWriter.write(String.valueOf(end) + "\n");
                                        myWriter.close();
                                    } catch (IOException e) {
                                        System.out.println("An error occurred.");
                                        e.printStackTrace();
                                   }
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
