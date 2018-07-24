package main.utils;

import main.Main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerWrapper {
    public static final String GLOBAL_LOGGER_NAME = "_logFile_";
    public static final String LOGGER_LOCATION = Main.recorder.getSaveLocation();
    public static Logger logger;
    static public void setup() throws IOException {
        logger = Logger.getLogger(GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.INFO);
        FileHandler fileTxt = new FileHandler(LOGGER_LOCATION + "/log.txt");
        fileTxt.setFormatter(new LogFormatter());
        logger.addHandler(fileTxt);
    }

}

class LogFormatter extends Formatter {
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    @Override
    public synchronized String format(LogRecord logRecord) {
        StringBuilder sb = new StringBuilder();
        if(logRecord.getLevel() != Level.WARNING){
            sb.append(logRecord.getLevel());
        }else {
            sb.append("INFO");
        }
        sb.append(":");
        Date date = new Date(logRecord.getMillis());
        sb.append(df.format(date));
        if(logRecord.getLevel() != Level.WARNING){
            sb.append(":");
            sb.append(Main.player.getCurrentName());
        }
        sb.append(":");
        sb.append(logRecord.getMessage());
        sb.append("\n");
        return sb.toString();
    }
}
