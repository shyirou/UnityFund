package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemplateChatAdmin {

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField tfMessage;

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    private String xmlFilePath;

    private String username = "admin"; 

    public void initialize() {
        try {
            // Generate the XML file path based on the username
            xmlFilePath = "C:\\Users\\lenovo\\Documents\\UnityFund\\UnityFund\\src\\data\\chatUser.xml";

            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                createNewXML();
            }
            loadMessagesFromXML();

            // Set custom cell factory for chat bubbles
            listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> listView) {
                    return new ChatBubbleCell();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNewXML() {
        try {
            document = documentBuilder.newDocument();
            Element rootElement = document.createElement("chat");
            document.appendChild(rootElement);
            saveXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMessagesFromXML() {
        try {
            document = documentBuilder.parse(new File(xmlFilePath));
            NodeList nodeList = document.getElementsByTagName("message");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String text = element.getElementsByTagName("text").item(0).getTextContent();
                    listView.getItems().add(text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickKirim(ActionEvent event) {
        try {
            String message = tfMessage.getText();
            if (message.isEmpty()) {
                return;
            }
            tfMessage.clear();
            addMessageToXML(message);
            listView.getItems().add(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMessageToXML(String message) {
        try {
            Element root = document.getDocumentElement();
            Element newMessage = document.createElement("message");

            Element text = document.createElement("text");
            text.appendChild(document.createTextNode(message));
            newMessage.appendChild(text);

            Element timestamp = document.createElement("timestamp");
            timestamp.appendChild(document.createTextNode(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            newMessage.appendChild(timestamp);

            root.appendChild(newMessage);
            saveXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveXML() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ChatBubbleCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                HBox hbox = new HBox();
                VBox vbox = new VBox();
                Label label = new Label(item);
                label.setWrapText(true);
                label.getStyleClass().add("chat-bubble");

                vbox.getChildren().add(label);
                HBox.setHgrow(vbox, Priority.ALWAYS);
                hbox.getChildren().add(vbox);
                setGraphic(hbox);
            }
        }
    }
}
