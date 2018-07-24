## Audio Recording Application

### Instructions

1. Download the archive & extract it.

2. Run the script ./run.sh

3. Choose the output directory (The directory in which the files will be saved).

4. Choose the input directory (The directory containing the .wav files to be played)

The main window allows you to listen to the audio by pressing the play button.

Record your answer by clicking once on the repeat button (click again to stop recording). You can repeat the same procedure for the Translate & Comment buttons.

You can go back and record again if you want to add something extra all the recordings will be appended.

Right now the application creates 3 recordings per audio input (Repeat, Translate, Comment).

The files are saved when you click on next and three more are created for the next audio input and this repeats until we go through all the files.

In the input folder a log file is created with a list of the users actions.


### Dependencies

Java needs to be installed (Preferably 1.8 or better).

The Recording part is done using the [minim](https://github.com/ddf/Minim) audio library.


## Compilation

The project is compiled using intelij, just clone the repo and import it into intelij.

You might need to re add the libs folder into the target jar file.