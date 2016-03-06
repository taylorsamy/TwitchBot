package sample;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Sample custom control hosting a text field and a button.
 */
public class RequestControl extends HBox {
    private static int id = 0;
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    private VBox layout;

    public RequestControl(VBox layout, String message) {
        this.layout = layout;
        this.setId(String.valueOf(id));
        id++;
        Button copyBtn = new Button("Copy");
        Button delBtn = new Button("Remove");
        Label label = new Label(message);

        copyBtn.setOnAction(event -> copy(message));
        delBtn.setOnAction(event -> delete());

        label.setMinSize(250, USE_COMPUTED_SIZE);
        label.setPadding(new Insets(4, 10, 4, 10));
        this.getChildren().add(label);
        this.getChildren().add(copyBtn);
        this.getChildren().add(delBtn);

    }

    private void copy(String message) {

//put the message into the clipboard
        content.putString(message);
        clipboard.setContent(content);

        //animate and remove the node if there is only one node on the screen
        if (layout.getChildren().size() == 1) {
            TranslateTransition closeNav = new TranslateTransition(new Duration(350), this);
            closeNav.setToY(-this.getHeight());
            closeNav.play();
            closeNav.setOnFinished(event -> {
                layout.getChildren().remove(this);
            });
        }


        for (Node node : layout.getChildren()) {
            int idNum = Integer.valueOf(node.getId());


            if (idNum > Integer.valueOf(this.getId())) { // only animate nodes below the one clicked


                TranslateTransition closeNav = new TranslateTransition(new Duration(350), node);
                closeNav.setToY(-this.getHeight());
                closeNav.play();

                closeNav.setOnFinished(event -> {
                    layout.getChildren().remove(this);
                    layout.getChildren().stream().filter(node2 -> idNum > Integer.valueOf(this.getId())).forEach(node2 -> {
                        TranslateTransition closeNav2 = new TranslateTransition(new Duration(1), node2);
                        closeNav2.setToY(0);
                        closeNav2.play();
                    });
                });
            }

        }
    }

    private void delete() {
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), this);
        closeNav.setToX(this.getWidth());
        closeNav.play();
        closeNav.setOnFinished(event -> {
            layout.getChildren().remove(this);

        });


    }
}
