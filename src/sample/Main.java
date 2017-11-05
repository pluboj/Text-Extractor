package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Text Extractor v1.0");


        /*ChoiceBox*/
        ChoiceBox<String> documents = new ChoiceBox<>();
        documents.getItems().addAll("Word", "Excel");

        /*Text input*/
        TextField textUrl = new TextField();
        textUrl.setPromptText("Paste Url");
        textUrl.setPrefWidth(500);

        /*Grid Pane*/
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        /*Labels*/
        Label lblDocuments = new Label("Select type of document: ");
        lblDocuments.setLabelFor(documents);

        Label lblTextUrl = new Label("Paste URL: ");
        lblTextUrl.setLabelFor(textUrl);

        /*Add elements to gridPane*/
        gridPane.addRow(0,lblDocuments);
        gridPane.addRow(1, documents);
        gridPane.addRow(2, lblTextUrl);
        gridPane.addRow(3, textUrl);

        Scene scene = new Scene(gridPane, 500, 350);
        scene.getStylesheets().add("/resources/css/main.css");
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
