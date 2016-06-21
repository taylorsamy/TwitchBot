package test;

import org.jibble.pircbot.PircBot;
import test.modules.CommandModule;

import java.util.ArrayList;


/**
 * Created by Taylor on 20/06/2016.
 */
public class Bot extends PircBot {
    public static final String CHANNEL = "#taylorsamy";
    ArrayList<Module> modules = new ArrayList<>();


    public Bot() {
        this.setName("DwagonBot");
        this.isConnected();
        CommandModule commandModule = new CommandModule();
        modules.add(commandModule);
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        for (Module module : modules) {
            module.receiveMessage(sender, message);
        }
    }
}
