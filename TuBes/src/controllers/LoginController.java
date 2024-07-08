package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.User;
import models.XMLHandler;
import views.ViewFactory;

import java.util.List;

public class LoginController {

    ViewFactory m = new ViewFactory();

    @FXML
    private Button btnLogin;

    @FXML
    private Button creat;

    @FXML
    private AnchorPane pageLogin;

    @FXML
    private PasswordField tfPass;

    @FXML
    private TextField tfUsername;

    @FXML
    private ComboBox<String> pilihan;

    @FXML
    void clickCreat(ActionEvent event) {
        String selectedRole = pilihan.getValue();
        if ("Mahasiswa".equals(selectedRole)) {
            Stage stage = (Stage) pageLogin.getScene().getWindow();
            m.nextPagetoSignUp(stage);
        } else if ("Admin".equals(selectedRole)) {
            showAlert("Access Denied", "Sign-up is only available for Mahasiswa.");
        } 
        
    }

    @FXML
    void clickLogin(ActionEvent event) {
        String username = tfUsername.getText();
        String password = tfPass.getText();
        String selectedRole = pilihan.getValue();

        if ("Admin".equals(selectedRole)) {
            if (username.equals("admin") && password.equals("pass123")) {
                Stage stage = (Stage) pageLogin.getScene().getWindow();
                m.nextPageAdmin(stage);
                showAlert("Admin Login Successful", "Welcome, Admin!");
            } else {
                showAlert("Admin Login Failed", "Invalid username or password for admin.");
            }
        } else {
            handleStudentLogin(username, password);
        }
    }

    private void handleStudentLogin(String username, String password) {
        List<User> users = XMLHandler.readUsers(XMLHandler.STUDENT_FILE_PATH);

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Stage stage = (Stage) pageLogin.getScene().getWindow();
                m.nextPageHome(stage);
                showAlert("Login Successful", "Welcome, " + username + "!");
                return;
            }
        }
        showAlert("Login Failed", "Invalid username or password.");
    }

    @FXML
    public void initialize() {
        pilihan.setItems(FXCollections.observableArrayList("Mahasiswa", "Admin"));
        pilihan.setValue("Mahasiswa");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
