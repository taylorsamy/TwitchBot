package test.modules;

import test.Bot;
import test.Main;
import test.Module;
import test.utils.CommandUtils;
import test.utils.SenderUtils;


/**
 * Created by Taylor on 21/06/2016.
 */
public class CommandModule extends Module {

    CommandUtils cmdUtils = new CommandUtils();
    SenderUtils senderUtils = new SenderUtils();

    public void receiveMessage(String sender, String message) {
        String[] messageArray = message.split(" ", 2);
        if (senderUtils.ckechRank(sender, 3)) {

            if (message.toLowerCase().startsWith("+cmd add ")) {
                String[] tmp = message.split(" ", 5);
                if (tmp.length != 5) {
                    Main.bot.sendMessage(Bot.CHANNEL, "Invalid syntax");
                    return;
                }
                String result = cmdUtils.searchCommands(tmp[2], senderUtils.getRank(sender));
                if (result != null) {
                    Main.bot.sendMessage(Bot.CHANNEL, "That command already exists");
                    return;
                }
                cmdUtils.addCommand(tmp[2], Integer.parseInt(tmp[3]), tmp[4]);
                Main.bot.sendMessage(Bot.CHANNEL, tmp[2] + " has been added");

            } else if (message.toLowerCase().startsWith("+cmd del ")) {
                String[] tmp = message.split(" ", 3);
                int result = cmdUtils.deleteCommand(tmp[2]);
                if (result == 1) {
                    Main.bot.sendMessage(Bot.CHANNEL, "The command has been deleted");
                }
            } else if (message.toLowerCase().startsWith("+rank set")) {
                String[] tmp = message.split(" ", 4);
                senderUtils.setRank(Integer.parseInt(tmp[3]), tmp[2]);
            }
        }

        String command = cmdUtils.searchCommands(messageArray[0], senderUtils.getRank(sender));
        if (command != null) {
            if (messageArray.length == 1) {
                Main.bot.sendMessage(Bot.CHANNEL, command);
            } else {
                Main.bot.sendMessage(Bot.CHANNEL, command.replace("%user%", sender).replace("%args%", messageArray[1]));
            }
        }
    }
}
