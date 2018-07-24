package main.soundControllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Playlist {
    private ArrayList<File> listOfFiles = null;
    private int current = 0;

    Playlist(){ }

    public String getCurrent() throws PlaylistNotFoundException, IndexOutOfBoundsException  {
        if(listOfFiles == null || listOfFiles.isEmpty()){
            throw new PlaylistNotFoundException();
        }
        return listOfFiles.get(current).getAbsolutePath();
    }

    public void next() throws PlaylistOutOfBoundsException {
        current++;
        if(current <= 0 && current >= listOfFiles.size()){
            current--;
            throw new PlaylistOutOfBoundsException();
        }
    }

    public void setFolderLocation(File location) {
        current = 0;
        if(location.isDirectory()){
            File[] lst = location.listFiles((dir, name) -> name.endsWith(".wav"));
            listOfFiles = new ArrayList<>(Arrays.asList(lst));
        }
    }

    public String getCurrentName() {
        return this.listOfFiles.get(current).getName();
    }

    /** Playlist Exceptions */
    public class PlaylistOutOfBoundsException extends Exception {    }
    public class PlaylistNotFoundException extends Exception { }
}
