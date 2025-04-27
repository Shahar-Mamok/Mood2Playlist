package view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.PrimitiveIterator;

public class MainView extends Application {
    private TextField MoodInput;
    private Button GentatePlaylist;
    private TextArea resultArea;


    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Start Vibing");

        MoodInput = new TextField();
        GentatePlaylist = new Button("Gentate Playlist");
        resultArea = new TextArea();
        resultArea.setEditable(false);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, MoodInput, GentatePlaylist, resultArea);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Mood2Playlist");
        primaryStage.setScene(scene);
        new controller.PlaylistController(this);

        primaryStage.show();

    }
    public TextField getMoodInput() {
        return MoodInput;
    }
    public Button getGenerateButton() {
        return  GentatePlaylist;
    }
    public TextArea getResultArea() {
        return resultArea;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
