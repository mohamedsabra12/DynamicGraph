package server;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import graph.IGraph;

public class ReaderCallable implements Callable{

            private final int src,des,index;
            private final IGraph graph;
            private final int[] result;
            public ReaderCallable(int index, int src , int des,IGraph graph,int [] result){
                this.index = index;
                this.src = src;
                this.des = des;
                this.graph = graph;
                this.result = result;
            }

            public Integer call() {
                this.result[index] = graph.query(src,des);
                //System.out.println("result of index: " + index + " is " + this.result[index]);
                return 0;
            }
}

