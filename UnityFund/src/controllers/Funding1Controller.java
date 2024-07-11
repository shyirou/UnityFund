package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.XMLAjukanFundingHandler;
import views.ViewFactory;

public class Funding1Controller {

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
    private AnchorPane Funding1;

    @FXML
    private Button btAjukan;

    @FXML
    private TextField textBackground;

    @FXML
    private TextField textBudget;

    @FXML
    private TextField textLikes;

    @FXML
    private TextField textObjective;

    @FXML
    private TextField textTeamMember;

    @FXML
    private TextField textTeamName;

    @FXML
    private TextField textTitle;



    @FXML
    void LogOut(ActionEvent event) {
        Stage stage = (Stage) Funding1.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    @FXML
    void klikCollab(ActionEvent event) {
        Stage stage = (Stage) Funding1.getScene().getWindow();
        m.nextPageCollab(stage);

    }

    @FXML
    void klikProject(ActionEvent event) {
        Stage stage = (Stage) Funding1.getScene().getWindow();
        m.nextPageHome(stage);
    }

    @FXML
    void klikSub(ActionEvent event) {
        Stage stage = (Stage) Funding1.getScene().getWindow();
        m.nextPageSub(stage);

    }
    @FXML
    void klikFunding(ActionEvent event) {
        

    }

    

    @FXML
    void klikAjukanFunding(ActionEvent event) {
        if (validateInput()) {
            XMLAjukanFundingHandler.writeFunding(
                textTitle.getText(),
                textBackground.getText(),
                textObjective.getText(),
                textTeamName.getText(),
                textTeamMember.getText(),
                textBudget.getText(),
                textLikes.getText()
            );
            showAlert("Success", "Data saved successfully!", Alert.AlertType.INFORMATION);
            clearFields();
        }
    }

    private boolean validateInput() {
        if (textTitle.getText().isEmpty() || textBackground.getText().isEmpty() || textObjective.getText().isEmpty() ||
            textTeamName.getText().isEmpty() || textTeamMember.getText().isEmpty() || textBudget.getText().isEmpty() ||
            textLikes.getText().isEmpty()) {
            showAlert("Error", "All fields must be filled out", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        textTitle.clear();
        textBackground.clear();
        textObjective.clear();
        textTeamName.clear();
        textTeamMember.clear();
        textBudget.clear();
        textLikes.clear();
    }
}
    


