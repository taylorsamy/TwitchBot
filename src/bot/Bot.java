package bot;

import org.jibble.pircbot.PircBot;

/**
 * Created by Taylor on 24/02/2016.
 */
public class Bot extends PircBot {
    public static final String CHANNEL = "#taylorsamy";
    public Test interfaceCallback;

    public Bot() {
        this.setName("DwagonBot");
        this.isConnected();
        sendMessage(CHANNEL, "THIS");
        sendMessage(CHANNEL, "IS");
        sendMessage(CHANNEL, "DWAGONBOT");
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

        if (message.startsWith("!request")) {

            message = message.replace("!request ", "");

            if (message.matches("(\\d+)")) { //if message is numeric
                interfaceCallback.request(message);
                sendMessage(CHANNEL, "Request accepted");

            } else {
                sendMessage(CHANNEL, "dumbass");
            }
        } else if (message.toLowerCase().contains("dwagonbot")) {
            sendMessage(CHANNEL, "RAWR");
        }
    }

    public interface Test {
        void request(String message);
    }
}
