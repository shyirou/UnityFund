package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class NewFundings {

    @FXML
    private Button hapusLaporan;

    @FXML
    private TableView<Funding> table;

    @FXML
    private TableColumn<Funding, String> colTitle;

    @FXML
    private TableColumn<Funding, String> colTeamName;

    @FXML
    private TableColumn<Funding, String> colBudget;

    @FXML
    private TableColumn<Funding, Integer> colLikes;

    private ObservableList<Funding> fundingList;

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTeamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        colBudget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        colLikes.setCellValueFactory(new PropertyValueFactory<>("likes"));

        fundingList = FXCollections.observableArrayList();
        loadFundingData();
        table.setItems(fundingList);

        // Log for debugging
        System.out.println("Funding list loaded: " + fundingList);

        // Handle row selection to update the selected funding item
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Single-click
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    // Set the selected item to be deleted
                    Funding selectedFunding = table.getSelectionModel().getSelectedItem();
                    if (selectedFunding != null) {
                        // Log for debugging
                        System.out.println("Selected funding: " + selectedFunding);
                    }
                }
            }
        });
    }

    private void loadFundingData() {
        try {
            File inputFile = new File("C:\\Users\\lenovo\\Documents\\UnityFund\\UnityFund\\src\\data\\funding.xml");
            if (!inputFile.exists()) {
                System.out.println("Funding XML file not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("funding");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String teamName = eElement.getElementsByTagName("teamName").item(0).getTextContent();
                    String budget = eElement.getElementsByTagName("budget").item(0).getTextContent();
                    int likes;
                    try {
                        likes = Integer.parseInt(eElement.getElementsByTagName("likes").item(0).getTextContent());
                    } catch (NumberFormatException e) {
                        likes = 0; // Default value or handle the error as needed
                        System.out.println("Invalid number format in likes: " + eElement.getElementsByTagName("likes").item(0).getTextContent());
                    }
                    fundingList.add(new Funding(title, teamName, budget, likes));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHapusLaporan(ActionEvent event) {
        // Get selected item
        Funding selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Log for debugging
            System.out.println("Removing funding: " + selectedItem);

            // Remove from TableView
            table.getItems().remove(selectedItem);
            // Remove from fundingList
            fundingList.remove(selectedItem);

            // Update XML file
            updateXMLFile();
        } else {
            // Handle case where no item is selected
            System.out.println("No funding selected to delete.");
        }
    }

    private void updateXMLFile() {
        try {
            // Create a new Document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Root element
            Element rootElement = doc.createElement("fundings");
            doc.appendChild(rootElement);

            // Add each funding in fundingList to the XML
            for (Funding funding : fundingList) {
                Element fundingElement = doc.createElement("funding");
                rootElement.appendChild(fundingElement);

                // Add child elements
                createElement(doc, fundingElement, "title", funding.getTitle());
                createElement(doc, fundingElement, "teamName", funding.getTeamName());
                createElement(doc, fundingElement, "budget", funding.getBudget());
                createElement(doc, fundingElement, "likes", String.valueOf(funding.getLikes()));
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\lenovo\\Documents\\UnityFund\\UnityFund\\src\\data\\funding.xml"));
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
