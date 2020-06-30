package tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Wolfman
 */
public class Logger {
    public static void log(String message) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("FishScr.log", true))) {
            String timeLog = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss")
                    .format(Calendar.getInstance().getTime());
            w.write(timeLog + "\t| " + message + "\n");
        } 
        catch (Exception e) {
        }
    }
}
