package main;


/** Audio */
import main.soundControllers.Player;
import main.soundControllers.Recorder;
/** UI */
import main.ui.FileSelector;
import main.ui.Window;
import main.utils.LoggerWrapper;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static Recorder recorder;
    public static Player player;
    public static Window window;


    public static void main(String[]args) throws IOException {
        recorder = new Recorder();
        player = new Player();
        LoggerWrapper.setup();
        SwingUtilities.invokeLater(() -> {
           window = new Window("E-Recorder");
           window.setVisible(true);
        });
    }

    public static void main2(String[] args) throws IOException {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("file1.wav");
        lst.add("file2.wav");
        lst.add("file3.wav");
        FileSelector dialog = new FileSelector(lst);
        dialog.setOnOk(e -> System.out.println("Chosen item: " + dialog.getSelectedItem()));
        dialog.setOnClose(e -> {System.out.println("Bad Choice"); System.exit(0);});
        dialog.show();
        ArrayList<String> result = dialog.getSelectedItem();
        result.forEach(System.out::println);
    }


    public static void main3(String[]args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        for (Mixer.Info m : AudioSystem.getMixerInfo()) {
            System.out.println("    " + m);
        }

        Clip clip;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/home/nhamilakis/input/p225_001.wav"));
        AudioFormat format = audioInputStream.getFormat();
        System.out.println(format.toString());
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getClip(null);
        System.out.println("Clip format: " + clip.getFormat());
        clip.open(audioInputStream);

        clip.start();
        do {
            Thread.sleep(100);
        } while (clip.isRunning());
    }
}

