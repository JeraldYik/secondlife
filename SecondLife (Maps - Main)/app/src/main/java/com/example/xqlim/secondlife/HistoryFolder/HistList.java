package com.example.xqlim.secondlife.HistoryFolder;

import android.content.Context;
import android.util.Log;

import com.example.xqlim.secondlife.RecyclablesFolder.Recyclable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HistList extends ArrayList<Recyclable> {

    // Constant with a file name
    public static String filename = "hist_list.ser";
    public static String TAG = "HistList";

    public HistList() {
        super();
    }

    public void writeToMem(Context context) {
        //open datafile
        try {
            Log.d(TAG,"1");
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            Log.d(TAG,"2");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            Log.d(TAG,"2.5");
            oos.writeObject(this);
            Log.d(TAG,"3");
            oos.close();
            fos.close();
            Log.d(TAG,"4");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HistList readFromMem(Context context) {

        HistList histlist = null;

        try
        {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            histlist = (HistList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return null;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return histlist;
    }
}
