package bot;

import org.jibble.pircbot.PircBot;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Taylor on 24/02/2016.
 */
public class Bot extends PircBot {
    public static final String CHANNEL = "#taylorsamy";
    public Test interfaceCallback;
    String regex = "[a-zA-Z\\d]+://(\\w+:\\w+@)?([a-zA-Z\\d.-]+\\.[A-Za-z]{2,4})(:\\d+)?(/.*)?";
    Pattern p = Pattern.compile(regex);
    private ArrayList<String> requestedLevels = new ArrayList<>();


    public Bot() {
        this.setName("DwagonBot");
        this.isConnected();

    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        System.out.print(channel + " " + sender + " " + login + " " + hostname + " " + message);
        Matcher m = p.matcher(message);
        if (m.find()) {
            sendMessage(CHANNEL, "URL DETECTED");

        }
        if (message.toLowerCase().startsWith("!request ")) {

            message = message.toLowerCase().replace("!request ", "");

            if (message.matches("(\\d+)")) { //if message is numeric
                if (requestedLevels.contains(message)) {
                    sendMessage(CHANNEL, "I'm sorry " + sender + ", but that level has already been requested this stream. Please request a different level");

                } else {
                    requestedLevels.add(message);
                    interfaceCallback.request(message);
                    sendMessage(CHANNEL, "Request accepted");
                }
            } else {
                sendMessage(CHANNEL, "dumbass");
            }
        }
    }

    public interface Test {
        void request(String message);
    }
}
