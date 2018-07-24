## Audio Recording Application

### Instructions

1. Download the [archive](https://github.com/bootphon/erec/releases) & extract it.

2. Go to the extracted archive and run the script "sh ./run.sh"

3. Choose the output directory (The directory in which the files will be saved - you may need to create this directory beforehand).

4. Choose the input directory (The directory containing the .wav files to be played- you have a "test_input" folder in the archive to test the app)

5. UI : 

The main window allows you to listen to the audio by pressing the play button. It plays the whole file. You can listen to it as many times as you wish by just pressing the play button.

Record your answer by clicking once on the "repeat" button (click again to stop recording). You can continue the recording by pressing the "repeat" button again. All recordings will be appended into one file --> InputFilename.wav_answer.wav

You can repeat the same procedure for the "Translate" & "Comment" buttons --> InputFilename.wav_answer_translated.wav & InputFilename.wav_comment.wav
Note that you cannot proceed to the next task ("Translate" or "Comment") unless you stop the recording for the current task.
Right now the application creates 3 recordings per audio input file ("Repeat", "Translate", "Comment").

The files are saved when you click on the "next" button and three more are created for the next audio input file. This repeats until we go through all the files.

In the output folder a "log" file is created with a list of the users actions (timestamps when the user performs an action: Played the sample, Started recording answer, Stopped recording translation, Saved & going to next sample...)


### Dependencies

Java needs to be installed (Preferably 1.8 or better).

For ubuntu/debian run : `apt-get install default-jdk`

> `$ java -version`
> openjdk version "1.8.0_171"

For ubuntu/debian run : `apt-get install default-jdk`

The Recording part is done using the [minim](https://github.com/ddf/Minim) audio library.

## Compilation

The project is compiled using intelij, just clone the repo and import it into intelij.

You might need to re add the libs folder into the target jar file.
