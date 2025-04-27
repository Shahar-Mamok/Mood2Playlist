package controller;

import ai.AiService;
import ai.HuggingFaceService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

import java.util.List;

public class PlaylistController {

    private MainView view;

    public PlaylistController(MainView view) {
        this.view = view;
        initListeners();
    }
    private AiService aiService = new HuggingFaceService();

    private void initListeners() {
        // Connect the button in the future
        view.getGenerateButton().setOnAction(event -> {
            String moodText = view.getMoodInput().getText();  // ← יצירת moodText
            try {
                List<String> keywords = aiService.getKeywordsFromMood(moodText);
                view.getResultArea().setText(String.join(", ", keywords));
            } catch (Exception e) {
                view.getResultArea().setText("EROR CONNCTION TO AI");
                e.printStackTrace();
            }
        });


    }
}
