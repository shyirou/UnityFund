package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import views.ViewFactory;
import java.io.File;

public class AdminController {

    ViewFactory m = new ViewFactory();

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button Chat;

    @FXML
    private Button Dashboard;

    @FXML
    private Button btFunding;

    @FXML
    private Button btLogOut;

    @FXML
    private Button btProject;

    @FXML
    private BarChart<String, Number> barchart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void initialize() {
        xAxis.setLabel("Category");
        yAxis.setLabel("Count");
        loadFundingData();
        loadProjectData();
    }

    @FXML
    void klikChat(ActionEvent event) {
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        m.nextPageChatAdmin(stage);
    }

    @FXML
    void klikFunding(ActionEvent event) {
        m.lihatDataFunding();
        
    }

    @FXML
    void klikLogOut(ActionEvent event) {
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    @FXML
    void klikProject(ActionEvent event) {
        m.lihatDataProject();
    }

    @FXML
    void klikDashboard(ActionEvent event) {
        // Add functionality for dashboard button
    }

    private void loadFundingData() {
        try {
            File inputFile = new File("C:\\Users\\lenovo\\Downloads\\TuBes\\TuBes\\src\\data\\funding.xml");
            if (!inputFile.exists()) {
                System.out.println("Funding XML file not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("funding");

            XYChart.Series<String, Number> fundingSeries = new XYChart.Series<>();
            fundingSeries.setName("Funding");

            int fundingCount = nList.getLength();
            fundingSeries.getData().add(new XYChart.Data<>("Funding", fundingCount));

            barchart.getData().add(fundingSeries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProjectData() {
        try {
            File inputFile = new File("C:\\Users\\lenovo\\Downloads\\TuBes\\TuBes\\src\\data\\projects.xml");
            if (!inputFile.exists()) {
                System.out.println("Projects XML file not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("project");

            XYChart.Series<String, Number> projectSeries = new XYChart.Series<>();
            projectSeries.setName("Projects");

            int projectCount = nList.getLength();
            projectSeries.getData().add(new XYChart.Data<>("Projects", projectCount));

            barchart.getData().add(projectSeries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
