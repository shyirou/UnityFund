package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Project;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.File;

public class NewProject {

    @FXML
    private Button hapusLaporan;

    @FXML
    private TableView<Project> table;

    @FXML
    private TableColumn<Project, String> colJudul;

    @FXML
    private TableColumn<Project, String> colLatar;

    @FXML
    private TableColumn<Project, String> colTujuan;

    @FXML
    private TableColumn<Project, String> colNama;

    @FXML
    private TableColumn<Project, String> colAnggota;

    private ObservableList<Project> projectList;

    @FXML
    public void initialize() {
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judulProject"));
        colLatar.setCellValueFactory(new PropertyValueFactory<>("latarBelakang"));
        colTujuan.setCellValueFactory(new PropertyValueFactory<>("tujuanKegiatan"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaTeam"));
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("anggotaTeam"));

        projectList = FXCollections.observableArrayList();
        loadProjectData();
        table.setItems(projectList);

        // Log for debugging
        System.out.println("Project list loaded: " + projectList);

        // Handle row selection to update the selected project item
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Single-click
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    // Set the selected item to be deleted
                    Project selectedProject = table.getSelectionModel().getSelectedItem();
                    if (selectedProject != null) {
                        // Log for debugging
                        System.out.println("Selected project: " + selectedProject);
                    }
                }
            }
        });
    }

    private void loadProjectData() {
        try {
            File inputFile = new File("C:\\Users\\lenovo\\Documents\\UnityFund\\UnityFund\\src\\data\\projects.xml");
            if (!inputFile.exists()) {
                System.out.println("Project XML file not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("project");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String judulProject = eElement.getElementsByTagName("judulProject").item(0).getTextContent();
                    String latarBelakang = eElement.getElementsByTagName("latarBelakang").item(0).getTextContent();
                    String tujuanKegiatan = eElement.getElementsByTagName("tujuanKegiatan").item(0).getTextContent();
                    String namaTeam = eElement.getElementsByTagName("namaTeam").item(0).getTextContent();
                    String anggotaTeam = eElement.getElementsByTagName("anggotaTeam").item(0).getTextContent();

                    projectList.add(new Project(judulProject, latarBelakang, tujuanKegiatan, namaTeam, anggotaTeam));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHapusLaporan(ActionEvent event) {
        // Get selected item
        Project selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Log for debugging
            System.out.println("Removing project: " + selectedItem);

            // Remove from TableView
            table.getItems().remove(selectedItem);
            // Remove from projectList
            projectList.remove(selectedItem);

            // Update XML file
            updateXMLFile();
        } else {
            // Handle case where no item is selected
            System.out.println("No project selected to delete.");
        }
    }

    private void updateXMLFile() {
        try {
            // Create a new Document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Root element
            Element rootElement = doc.createElement("projects");
            doc.appendChild(rootElement);

            // Add each project in projectList to the XML
            for (Project project : projectList) {
                Element projectElement = doc.createElement("project");
                rootElement.appendChild(projectElement);

                // Add child elements
                createElement(doc, projectElement, "judulProject", project.getJudulProject());
                createElement(doc, projectElement, "latarBelakang", project.getLatarBelakang());
                createElement(doc, projectElement, "tujuanKegiatan", project.getTujuanKegiatan());
                createElement(doc, projectElement, "namaTeam", project.getNamaTeam());
                createElement(doc, projectElement, "anggotaTeam", project.getAnggotaTeam());
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\lenovo\\Documents\\UnityFund\\UnityFund\\src\\data\\projects.xml"));
            transformer.transform(source, result);

            System.out.println("XML file updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createElement(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }
}
