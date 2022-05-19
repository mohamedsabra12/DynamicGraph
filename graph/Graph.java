package graph;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 

public class Graph implements IGraph{
    private Map<Integer,Set<Integer>> graph = new HashMap<>();


   public Graph(){
    try {
        File myObj = new File("graph\\init.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if(data.equals("S"))
                return;
            String [] str = data.split(" ");
            add(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
   }

    public void add(Integer src , Integer des){
         Set<Integer> neighbours = graph.getOrDefault(src,new HashSet<>());
         neighbours.add(des);
         graph.put(src,neighbours);
    };

    public void remove(Integer src , Integer des){
        if(!graph.containsKey(src))
             return;
       
        Set<Integer> neighbours = graph.get(src);
        if(neighbours.contains(des))
            neighbours.remove(des);
    };



    public Integer query(Integer src , Integer des){
         Queue<Integer> q = new ArrayDeque<>();
         Set<Integer> visited = new HashSet<>();

         q.add(src);
         int len = 0;
         while(!q.isEmpty()){
              int size = q.size();
              for(int i=0 ; i<size ; i++){
                  Integer node = q.poll();

                  if(visited.contains(node))
                      continue;

                  visited.add(node);    

                  if(node == des)
                        return len;

                  Set<Integer> children = graph.getOrDefault(node,new HashSet<>());
                  for(Integer child : children){
                      q.add(child);
                  }
              }
              len++;
         }

         return -1;
    };
}

