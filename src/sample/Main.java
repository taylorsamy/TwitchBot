
package sample;

import bot.Bot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements Bot.Test {
    VBox layout;

    /*
    *
    *
    * FEATURE REQUEST
    * Broadcast -- text field that sends message
    * COnfig files.
    *
    *
    * random rawr
    *
    *
    * */
    Bot bot;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        bot = new Bot();
        bot.interfaceCallback = this;
        bot.setVerbose(true);
        bot.connect("irc.twitch.tv", 6667, "oauth:sor43nnq1reyfkfnifs9dbmtwaejjd");
        bot.joinChannel(Bot.CHANNEL);

        primaryStage.setTitle("Dwagon Bot");
        layout = new VBox();
        BorderPane root = new BorderPane();
        layout.setMinSize(355, 50);
        layout.setMaxSize(355, 500);
        root.setRight(layout);

        TextField messageField = new TextField();

        messageField.setOnAction(e -> {
            bot.sendMessage(Bot.CHANNEL, messageField.getText());
            messageField.clear();
        });

        root.setBottom(messageField);


        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        request("test1");
        request("test2");
        request("test3");
        request("test4");
        request("test5");
        request("test6");
        request("test7");
        request("test8");
        request("test9");


    }

    @Override
    public void stop() throws Exception {
        super.stop();
        bot.dispose();
    }

    @Override
    public void request(String message) {

        Platform.runLater(() -> {
            RequestControl tst = new RequestControl(layout, message);
            layout.getChildren().add(tst);
        });
    }
}
