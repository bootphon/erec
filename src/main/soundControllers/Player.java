package main.soundControllers;


import main.Main;
import main.utils.LoggerWrapper;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;



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
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(currentTrack));
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
}