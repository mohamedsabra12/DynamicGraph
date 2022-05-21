package server;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.BufferedWriter;

public class Logger {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static BufferedWriter bufferWriter;

    static {
        try {
            bufferWriter = new BufferedWriter(new FileWriter("server//logger.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startExec(Integer id,String type,Integer NOR ,float upd,Integer numOfNodes,long startTime) {
        try {
            bufferWriter.write(id + ",::" + getTime() + "::," + type +",Number Of Requests: ,"+ NOR+ ",Number Of Nodes: ,"+ numOfNodes + ",Time is:," + (System.currentTimeMillis() - startTime) + ",ms delay.\n");
            bufferWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getTime() {
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}