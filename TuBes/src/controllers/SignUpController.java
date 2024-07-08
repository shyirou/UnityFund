package controllers;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.User;
import models.XMLHandler;
import views.ViewFactory;

public class SignUpController {

    ViewFactory m = new ViewFactory();

    @FXML
    private Button create;

    @FXML
    private TextField email;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private Button login;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField tfusername;

    @FXML
    private AnchorPane SignUp;

    private boolean allowedAccess = false;

    public void setAllowedAccess(boolean allowed) {
        this.allowedAccess = allowed;
    }

    @FXML
    void clickCreat(ActionEvent event) {
        if (!allowedAccess) {
            showAlert("Access Denied", "You are not authorized to access this page.");
            return;
        }
    
        String username = tfusername.getText();
        String password = pass.getText();
    
        // Check if the username already exists
        if (XMLHandler.userExists(username, XMLHandler.STUDENT_FILE_PATH)) {
            showAlert("Registration Failed", "Username already exists.");
            return;
        }
    
        // Write user to XML file
        XMLHandler.writeUser(new User(username, password), XMLHandler.STUDENT_FILE_PATH);
    
        showAlert("Registration Successful", "Welcome, " + username + "!");
        Stage stage = (Stage) SignUp.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    @FXML
    void clickLogin(ActionEvent event) {
        Stage stage = (Stage) SignUp.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
