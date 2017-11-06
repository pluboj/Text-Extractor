package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    ProcessWord processWord;

    ChoiceBox<String> documents;
    ProgressIndicator indeterminateInd;
    TextField textFile;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Text Extractor v1.0");

        /*ChoiceBox*/
        documents = new ChoiceBox<>();
        documents.getItems().addAll("Word", "Excel");

        /*ProgressBar*/
        indeterminateInd = new ProgressIndicator(0);

        /*Text input*/
        textFile = new TextField();
        textFile.setPromptText("Paste Url");
        textFile.setPrefWidth(500);

        /*Grid Pane*/
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        /*Buttons*/
        Button btnProcess = new Button("Process");
        btnProcess.setPrefWidth(500);
        btnProcess.setOnAction(e -> processDocument());

        /*Labels*/
        Label lblDocuments = new Label("Select type of document: ");
        lblDocuments.setLabelFor(documents);

        Label lblTextUrl = new Label("Paste file path: ");
        lblTextUrl.setLabelFor(textFile);

        /*Add elements to gridPane*/
        gridPane.addRow(0,lblDocuments);
        gridPane.addRow(1, documents);
        gridPane.addRow(2, lblTextUrl);
        gridPane.addRow(3, textFile);
        gridPane.addRow(6, btnProcess);
        gridPane.addRow(7, indeterminateInd);

        Scene scene = new Scene(gridPane, 500, 350);
        scene.getStylesheets().add("/resources/css/main.css");
        primaryStage.setScene(scene);



        primaryStage.show();
    }

    //TODO:pl - process doc
    private void processDocument() {
        String file = textFile.getText();
        String doc = documents.getValue();
        if (isInputValid (doc, file)) {
            if (doc.equalsIgnoreCase("word")) {
                processWord = new ProcessWord(file);
                System.out.println(processWord);
                processWord.processUrl();
            }
            System.out.println(documents.getValue());
            indeterminateInd.setProgress(1.0);
            textFile.clear();
            documents.setValue(null);
        }
    }

    private boolean isInputValid (String doc, String file) {
        return (doc != null && file != null && !file.isEmpty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
