package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import views.ViewFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class Project1Controller {

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
    private AnchorPane home;

    @FXML
    private AnchorPane anchorKotak;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane paneAtas;

    @FXML
    private Pane paneKiri;

    @FXML
    private Button infoNutric;

    @FXML
    private Button infoRobot;

    @FXML
    private HBox hboxGambar;

    @FXML
    void LogOut(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    @FXML
    void klikCollab(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        m.nextPageCollab(stage);
    }

    @FXML
    void klikFunding(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        m.nextPageFunding(stage);
    }

    @FXML
    void klikSub(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        m.nextPageSub(stage);
    }

    @FXML
    void klikProject(ActionEvent event) {
        // Implementasi jika diperlukan
    }

    @FXML
    void klikAsk(ActionEvent event) {
        Stage stage = (Stage) home.getScene().getWindow();
        m.nextPageCollab(stage);
    }

    @FXML
    void infoNutric(ActionEvent event) {
        m.lihatNutric();
    }

    @FXML
    void infoRobot(ActionEvent event) {
        m.lihatRobot();
    }

    private void loadProjects() {
        try {
            File xmlFile = new File("C:\\Users\\lenovo\\Documents\\UnityFund\\UnityFund\\src\\data\\projects.xml");
            if (!xmlFile.exists()) {
                System.out.println("File projects.xml tidak ditemukan");
                return;
            }

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            NodeList projectList = document.getElementsByTagName("project");
            for (int i = 0; i < projectList.getLength(); i++) {
                Element projectElement = (Element) projectList.item(i);

                String judul = projectElement.getElementsByTagName("judulProject").item(0).getTextContent();
                String imagePath = null;
                NodeList imageList = projectElement.getElementsByTagName("image");
                if (imageList.getLength() > 0) {
                    imagePath = imageList.item(0).getTextContent();
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Kotak.fxml"));
                Node node = loader.load();

                KotakController kotakController = loader.getController();
                kotakController.setJudulProject(judul);
                if (imagePath != null) {
                    File imageFile = new File(imagePath);
                    if (imageFile.exists()) {
                        Image image = new Image(imageFile.toURI().toString());
                        kotakController.setImage(image);
                    }
                }

                hboxGambar.getChildren().add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        hboxGambar.setSpacing(35); // Menambahkan spasi antar kotak
        loadProjects();
    }

    
}
