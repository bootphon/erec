package main.soundControllers;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.Recordable;
import main.Main;
import main.utils.LoggerWrapper;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Player {
	private Playlist playlist;
	private String currentTrack= null;


	public Player(){
		playlist = new Playlist();
		openNewPlaylist();
	}


	public void setPlaylist(File folder) throws Playlist.PlaylistNotFoundException {
		playlist.setFolderLocation(folder);
		currentTrack =  playlist.getCurrent();
	}

	public void play() {
		try{
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

		}catch (Exception e){

		}
	}

	public void nextTrack(){
		try {
			//Next Track
			playlist.next();
			//Get new file
			currentTrack = playlist.getCurrent();
			//Create new Recordings
			Main.recorder.init();
		} catch (IndexOutOfBoundsException |Playlist.PlaylistNotFoundException | Playlist.PlaylistOutOfBoundsException e) {
			LoggerWrapper.logger.warning(()-> "User completed task");
			Main.window.setVisible(false);
			JOptionPane.showMessageDialog(Main.window, "Congrats you have completed all the recordings");
			System.exit(0);
		}
	}

	public void openNewPlaylist(){
		JFileChooser f = new JFileChooser();
		f.setDialogTitle("Choose input directory");
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.showSaveDialog(null);
		File folder = f.getSelectedFile();
		System.out.println(folder);

		try {
			setPlaylist(folder);
		} catch (Playlist.PlaylistNotFoundException e) {
			System.out.println("Error Detecting playlist");
		}
	}

	public String getCurrentName() {

		return this.playlist.getCurrentName();
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
}