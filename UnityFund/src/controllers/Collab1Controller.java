package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.ViewFactory;

public class Collab1Controller {

    ViewFactory m = new ViewFactory();

    @FXML
    private Button Collab;

    @FXML
    private Button Funding;

    @FXML
    private Button LogOut;

    @FXML
    private Button Project;

    @FXML
    private Button ProjectSubmission;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lbnamaProfile;

    @FXML
    private AnchorPane Collab1;

    @FXML
    private AnchorPane tampilkanChat;

    @FXML
    private void initialize () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/TemplateChat.fxml"));
            AnchorPane chatPane = loader.load();
            tampilkanChat.getChildren().setAll(chatPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LogOut(ActionEvent event) {
        Stage stage = (Stage) Collab1.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    @FXML
    void klikAdmin(ActionEvent event) {
        Stage stage = (Stage) Collab1.getScene().getWindow();
        m.klikAdmin(stage);
    }

    @FXML
    void klikNutricTeam(ActionEvent event) {
        Stage stage = (Stage) Collab1.getScene().getWindow();
        m.klikNutricTeam(stage);

    }

   

    @FXML
    void klikFunding(ActionEvent event) {
        Stage stage = (Stage) Collab1.getScene().getWindow();
        m.nextPageFunding(stage);

    }

    @FXML
    void klikProject(ActionEvent event) {
        Stage stage = (Stage) Collab1.getScene().getWindow();
        m.nextPageHome(stage);
    }

    @FXML
    void klikSub(ActionEvent event) {
        Stage stage = (Stage) Collab1.getScene().getWindow();
        m.nextPageSub(stage);

    }

    @FXML
    void klikCollab(ActionEvent event) {

    }


}
