package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {

    public void nextPagetoSignUp(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/SignUpView.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UnityFund");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }

    public void nextPagetoLogin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/LoginView.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }

    public void nextPageHome (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Project1View.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }

    public void nextPageCollab (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Collab1.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }

    public void nextPageFunding (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Funding1.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }

    public void nextPageSub (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/ProSub1.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }


    public void nextPageChatAdmin (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/ChatAdmin.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }

  


    public void nextPageAdmin (Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Admin.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }



    public void loadAdminPage(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Admin.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
    }


    public void lihatRobot() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/lihatRobot.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the pop-up modal
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lihatNutric() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/lihatNutric.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the pop-up modal
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void klikAdmin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Collab2.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
        
     
    }

    public void klikNutricTeam(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Collab1.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error di page signup: " + e.getMessage());
        }
      
    }

    public void lihatDataFunding() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/NewFunding.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the pop-up modal
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lihatDataProject() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/NewProject.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the pop-up modal
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




   
  
}
