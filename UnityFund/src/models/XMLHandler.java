package models;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {

    public static final String STUDENT_FILE_PATH = "C:\\Users\\User\\Downloads\\TuBes\\UnityFund\\UnityFund\\src\\data\\user.xml";
    

    public static List<User> readUsers(String filePath) {
        List<User> users = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) return users;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("user");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();
                    users.add(new User(username, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeUser(User user, String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("users");
                doc.appendChild(rootElement);
            }

            Element root = doc.getDocumentElement();
            Element userElement = doc.createElement("user");

            Element username = doc.createElement("username");
            username.appendChild(doc.createTextNode(user.getUsername()));
            userElement.appendChild(username);

            Element password = doc.createElement("password");
            password.appendChild(doc.createTextNode(user.getPassword()));
            userElement.appendChild(password);

            root.appendChild(userElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean userExists(String username, String filePath) {
        List<User> users = readUsers(filePath);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
