package main.soundControllers;

import ddf.minim.AudioInput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import ddf.minim.Recordable;
import main.Main;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Recorder {
    private Minim minim;
    private AudioInput lineIn;

    private AudioRecorder rec1;
    private AudioRecorder rec2;
    private AudioRecorder rec3;
    private static String location;
    private String currentfilename;

    public Recorder(){
        minim = new Minim(this);
        //minim.debugOn();
        lineIn = minim.getLineIn();
        pickOutputLocation();
    }

    public void init(){
        currentfilename =  Main.player.getCurrentName();
        rec1 = minim.createRecorder(lineIn, location + "/" + currentfilename +  "_answer.wav");
        rec2 = minim.createRecorder(lineIn, location + "/" + currentfilename + "_answer_translated.wav");
        rec3 = minim.createRecorder(lineIn, location + "/" + currentfilename + "_comment.wav");
    }

    /** Start & Stop Recordings */

    public void record1(){
        rec1.beginRecord();
    }
    public void pause1(){
       rec1.endRecord();
    }
    public void record2(){
        rec2.beginRecord();
    }
    public void pause2(){
        rec2.endRecord();
    }
    public void record3(){
        rec3.beginRecord();
    }
    public void pause3(){
        rec3.endRecord();
    }


    /** Save & close all streams */
    public void saveAll(){
        System.out.println("Saving all recordings");
        rec1.save();
        rec2.save();
        rec3.save();
    }

    /* Dispose of minim instance*/
    public void dispose(){
        lineIn.close();
        minim.dispose();
    }


    /** Minim required methods */
    public String sketchPath( String fileName ){
        return fileName;
    }
    public InputStream createInput(String fileName ){
        try {
            return new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createRecorder(Recordable source, String filename){

    }
    /** End Minim required methods */


    /** Choose output dir */
    public void pickOutputLocation(){
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Choose output directory");
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        File folder = f.getSelectedFile();
        this.location = folder.getAbsolutePath();



    }

    public static String getSaveLocation() {
        return location;
    }
}
