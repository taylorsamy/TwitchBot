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
public class RequestControl extends VBox {
    private static int id = 0;
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    private HBox hBox;
    private VBox layout;

    public RequestControl(VBox layout, String message) {
        this.layout = layout;
        hBox = new HBox();
        hBox.setId(String.valueOf(id));
        this.setId(String.valueOf(id));
        id++;
        Button copyBtn = new Button("Copy");
        Button delBtn = new Button("Remove");
        Label label = new Label(message);

        copyBtn.setOnAction(event -> copy(message));
        delBtn.setOnAction(event -> delete());

        label.setMinSize(250, USE_COMPUTED_SIZE);
        label.setPadding(new Insets(4, 10, 4, 10));
        hBox.getChildren().add(label);
        hBox.getChildren().add(copyBtn);
        hBox.getChildren().add(delBtn);

        layout.getChildren().add(hBox);

    }

    private void copy(String message) {

//put the message into the clipboard
        content.putString(message);
        clipboard.setContent(content);

        //animate and remove the node if there is only one node on the screen
        if (layout.getChildren().size() == 2) { // 2 because hBox and RequestControl are both nodes that are added
            TranslateTransition closeNav = new TranslateTransition(new Duration(350), hBox);
            closeNav.setToY(-hBox.getHeight());
            closeNav.play();
            closeNav.setOnFinished(event -> {
                layout.getChildren().remove(this);
                layout.getChildren().remove(hBox);
            });
        }


        for (Node node : layout.getChildren()) {
            int idNum = Integer.valueOf(node.getId());


            if (idNum > Integer.valueOf(hBox.getId())) { // only animate nodes below the one clicked


                TranslateTransition closeNav = new TranslateTransition(new Duration(350), node);
                closeNav.setToY(-hBox.getHeight());
                closeNav.play();

                closeNav.setOnFinished(event -> {
                    layout.getChildren().remove(this);
                    layout.getChildren().remove(hBox);
                    layout.getChildren().stream().filter(node2 -> idNum > Integer.valueOf(hBox.getId())).forEach(node2 -> {
                        TranslateTransition closeNav2 = new TranslateTransition(new Duration(1), node2);
                        closeNav2.setToY(0);
                        closeNav2.play();
                    });
                });
            }

        }
    }

    private void delete() {
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), hBox);
        closeNav.setToX(hBox.getWidth());
        closeNav.play();
        closeNav.setOnFinished(event -> {
            layout.getChildren().remove(this);
            layout.getChildren().remove(hBox);

        });


    }
}
