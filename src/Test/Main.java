package test;

import org.jibble.pircbot.IrcException;

import java.io.IOException;

/**
 * Created by Taylor on 20/06/2016.
 */
public class Main {
    public static Bot bot;

    public Main() {

        bot = new Bot();
        bot.setVerbose(true);
        try {
            bot.connect("irc.twitch.tv", 6667, "oauth:sor43nnq1reyfkfnifs9dbmtwaejjd");//irc.chat.twitch.tv 443
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }


        bot.joinChannel(Bot.CHANNEL);
        bot.sendMessage(Bot.CHANNEL, "the Dwagonbot egg has been laid in your chat");


    }

    public static void main(String[] args) {
        new Main();
    }
}