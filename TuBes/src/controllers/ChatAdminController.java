package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.ViewFactory;

public class ChatAdminController {

    ViewFactory m = new ViewFactory();

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button Chat;

    @FXML
    private Button Dashboard;

    @FXML
    private Button btLogOut;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/TemplateChat.fxml"));
            AnchorPane chatPane = loader.load();
            anchorPane.getChildren().setAll(chatPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void klikChat(ActionEvent event) {

    }

    @FXML
    void klikDashBoard(ActionEvent event) {
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        m.nextPageAdmin(stage);

    }

    @FXML
    void klikLogOut(ActionEvent event) {
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        m.nextPagetoLogin(stage);

    }

}
