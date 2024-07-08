package controllers;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document; // Correct import for Document
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
    private void initialize() {
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

    private void hapusChatData() {
        try {
            // Path to the projects.xml file
            String filePath = "C:\\Users\\lenovo\\Downloads\\TuBes\\TuBes\\src\\data\\chatUser.xml";
            
            // Create a new XML document with an empty root element
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            // Create the root element
            doc.appendChild(doc.createElement("projects"));
            
            // Write the new document to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            
            System.out.println("Chat data deleted successfully.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void hapusChat(ActionEvent event) {
        hapusChatData();
    }
}
