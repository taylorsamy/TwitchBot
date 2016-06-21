package test;

import org.jibble.pircbot.IrcException;

import java.io.IOException;

/**
 * Created by Taylor on 20/06/2016.
 */
public class Main {
    public Main() {
        Bot bot;
        bot = new Bot();
        bot.setVerbose(true);
        try {
            bot.connect("irc.twitch.tv", 6667, "oauth:sor43nnq1reyfkfnifs9dbmtwaejjd");
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }

        bot.joinChannel(Bot.CHANNEL);

    }

    public static void main(String[] args) {
        new Main();
    }
}