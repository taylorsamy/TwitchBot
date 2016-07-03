package test.modules;

import test.Bot;
import test.Main;
import test.Module;
import test.utils.QuoteUtils;
import test.utils.SenderUtils;

/**
 * Created by Taylor on 03/07/2016.
 */
public class QuoteModule extends Module {

    SenderUtils senderUtils = new SenderUtils();
    QuoteUtils quoteUtils = new QuoteUtils();

    @Override
    public void receiveMessage(String sender, String message) {
        if (senderUtils.ckechRank(sender, 3)) {
            String[] messageArray = message.split(" ", 3);
            if (message.toLowerCase().startsWith("!quote add")) {
                quoteUtils.addQuote(messageArray[2]);
                Main.bot.sendMessage(Bot.CHANNEL, "Quote added successfully");
            } else if (message.toLowerCase().startsWith("!quote del")) {
                if (quoteUtils.deleteQuote(Integer.parseInt(messageArray[2])) == 1) {
                    Main.bot.sendMessage(Bot.CHANNEL, "Quote deleted successfully");
                }
            } else if (message.toLowerCase().startsWith("!quote")) {
                if (messageArray.length >= 2) {
                    Main.bot.sendMessage(Bot.CHANNEL, quoteUtils.getQuote(Integer.parseInt(messageArray[1])));
                } else {
                    Main.bot.sendMessage(Bot.CHANNEL, quoteUtils.getQuote((int) (quoteUtils.getQuotesNum() * Math.random() + 1)));
                }
            }
        }
    }
}
