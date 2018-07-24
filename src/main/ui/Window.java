package main.ui;

import main.Main;
import main.utils.LoggerWrapper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;


public class Window extends JFrame {
    public static JLabel fileLabel = new JLabel();
    //public static JLabel infoLabel = new JLabel();
    //TODO: add icons to buttons
//    private ImageIcon iconPlay = new ImageIcon("res/play.png");
//    private ImageIcon iconNext = new ImageIcon(String.valueOf(getClass().getResource("res/next.png")));
//    private ImageIcon iconRecord = new ImageIcon(String.valueOf(getClass().getResource("res/record.png")));

    public Window(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new JLabel(), BorderLayout.CENTER);
        setWindowSize(this);
        centerWindow(this);
        buildComponents();
    }

    private void buildComponents() {
        //Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        panel.setBackground(Color.WHITE);

        // Position containers
        JPanel topStuff = new JPanel(new BorderLayout());
        topStuff.setBackground(Color.WHITE);
        JPanel midlleStuff = new JPanel();
        midlleStuff.setBackground(Color.WHITE);
        JPanel bottomStuff = new JPanel();
        bottomStuff.setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(Color.WHITE, 20);
        //Info Label
        //infoLabel = new JLabel("Information about speakers goes here?", JLabel.CENTER);
        //infoLabel.setBackground(Color.WHITE);
        //infoLabel.setFont(new Font("Sans", Font.BOLD, 40));

        //infoLabel.setBorder(border);
        //infoLabel.setSize(new Dimension(120,40));

        // File Label
        fileLabel = new JLabel("", JLabel.CENTER);
        fileLabel.setBackground(Color.WHITE);
        fileLabel.setFont(new Font("Sans", Font.BOLD, 25));
        fileLabel.setBorder(border);
        fileLabel.setSize(new Dimension(120,40));
        setInfoLabel(Main.player.getCurrentName());


        topStuff.add(fileLabel,BorderLayout.NORTH );
        //topStuff.add(infoLabel, BorderLayout.SOUTH);



        //Play Button
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Sans", Font.BOLD, 14));
//        playButton.setIcon(iconPlay);
        playButton.addActionListener(actionEvent -> {
            LoggerWrapper.logger.info(() -> "Played the sample");
            Main.player.play();
        });
        playButton.setPreferredSize(new Dimension(120, 60));

        //Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Sans", Font.BOLD, 14));
//        nextButton.setIcon(iconNext);
        nextButton.addActionListener(actionEvent -> {
            LoggerWrapper.logger.info(() -> "Saved & going to next sample");
            //Save all previous recordings
            Main.recorder.saveAll();
            //Load & Play Next Track
            Main.player.nextTrack();
            setInfoLabel(Main.player.getCurrentName());
        });
        nextButton.setPreferredSize(new Dimension(120, 60));

        //Add Everything to panel
        midlleStuff.add(playButton);
        midlleStuff.add(nextButton);

        //Initialise Recorder
        Main.recorder.init();

        //TODO: make toggle record buttons switch when clicked

        //Recording
        JToggleButton rec1 = new JToggleButton("Repeat");
        rec1.setFont(new Font("Sans", Font.BOLD, 14));
//        rec1.setIcon(iconRecord);
        rec1.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED){
                LoggerWrapper.logger.info(() -> "Started recording answer");
                Main.recorder.record1();
            }else if(itemEvent.getStateChange()==ItemEvent.DESELECTED){
                LoggerWrapper.logger.info(() -> "Stopped recording answer");
                Main.recorder.pause1();
            }

        });
        rec1.setPreferredSize(new Dimension(200, 60));

        //Translation
        JToggleButton rec2 = new JToggleButton("Translate");
        rec2.setFont(new Font("Sans", Font.BOLD, 14));
//        rec2.setIcon(iconRecord);
        rec2.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED){
                LoggerWrapper.logger.info(() -> "Started recording translation");
                Main.recorder.record2();
            }else if(itemEvent.getStateChange()==ItemEvent.DESELECTED){
                LoggerWrapper.logger.info(() -> "Stopped recording translation");
                Main.recorder.pause2();
            }

        });
        rec2.setPreferredSize(new Dimension(200, 60));

        //Comment
        JToggleButton rec3 = new JToggleButton("Comment");
        rec3.setFont(new Font("Sans", Font.BOLD, 14));
//        rec3.setIcon(iconRecord);
        rec3.addItemListener(itemEvent -> {
            if(itemEvent.getStateChange()==ItemEvent.SELECTED){
                LoggerWrapper.logger.info(() -> "Started recording comment");
                Main.recorder.record3();
            }else if(itemEvent.getStateChange()==ItemEvent.DESELECTED){
                LoggerWrapper.logger.info(() -> "Stopped recording comment");
                Main.recorder.pause3();
            }

        });
        rec3.setPreferredSize(new Dimension(200, 60));

        bottomStuff.add(rec1);
        bottomStuff.add(rec2);
        bottomStuff.add(rec3);

        panel2.add(topStuff, BorderLayout.NORTH);
        panel2.add(midlleStuff, BorderLayout.CENTER);
        panel2.add(bottomStuff, BorderLayout.SOUTH);
        panel.add(panel2);

        this.add(panel);
    }
    /** Center window in the middle of the screen */
    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /** Set Window dimensions to half the screen */
    public static void setWindowSize(Window frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width/2;
        int height = screenSize.height/2;
        if(width > 960) width = 960;
        if(height > 540) height = 540;

        frame.pack();
        frame.setSize(width,height);
    }

    /* Modify Current Filename */
    public void setInfoLabel(String filename){
        fileLabel.setText("Recording file : " + filename);
    }
}