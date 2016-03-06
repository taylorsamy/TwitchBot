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
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

        if (message.startsWith("!request")) {
            try {
                message = message.substring(message.indexOf(' '));
            } catch (Exception e) {
                sendMessage(CHANNEL, "Invalid Syntax");
            }
            interfaceCallback.request(message);
            sendMessage(CHANNEL, "Request accepted");
        } else if (message.equalsIgnoreCase("!dwagoninfo")) {

            if (sender.equalsIgnoreCase("blooddragon262626")) {
                sendMessage(CHANNEL, "Hello Big Bad Dwagon! I am DwagonBot. RAWR");
            } else if (sender.equalsIgnoreCase("taylorsamy")) {
                sendMessage(CHANNEL, "Hello Smol baby Dwagon! I am DwagonBot. RAWR");

            } else {

                sendMessage(CHANNEL, "Hello " + sender + ", I am DwagonBot. RAWR");
            }
        }

    }

    public interface Test {
        void request(String message);
    }
}
