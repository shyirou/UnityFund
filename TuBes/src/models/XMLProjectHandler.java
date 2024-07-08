package models;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLProjectHandler {

    private static final String FILE_PATH = "C:\\Users\\User\\Downloads\\pull\\UnityFund\\TuBes\\src\\data\\project.xml";

    public static List<Project> readProjects() {
        List<Project> projects = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return projects;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("project");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String teamName = element.getElementsByTagName("teamName").item(0).getTextContent();
                    String background = element.getElementsByTagName("background").item(0).getTextContent();
                    String teamMembers = element.getElementsByTagName("teamMembers").item(0).getTextContent();
                    String objectives = element.getElementsByTagName("objectives").item(0).getTextContent();
                    projects.add(new Project(title, teamName, background, teamMembers, objectives));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static void writeProject(String title, String background, String objectives, String teamName, String teamMembers) {
        try {
            File file = new File(FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("projects");
                doc.appendChild(rootElement);
            }

            Element root = doc.getDocumentElement();
            Element projectElement = doc.createElement("project");

            Element titleElement = doc.createElement("title");
            titleElement.appendChild(doc.createTextNode(title));
            projectElement.appendChild(titleElement);

            Element backgroundElement = doc.createElement("background");
            backgroundElement.appendChild(doc.createTextNode(background));
            projectElement.appendChild(backgroundElement);

            Element objectivesElement = doc.createElement("objectives");
            objectivesElement.appendChild(doc.createTextNode(objectives));
            projectElement.appendChild(objectivesElement);

            Element teamNameElement = doc.createElement("teamName");
            teamNameElement.appendChild(doc.createTextNode(teamName));
            projectElement.appendChild(teamNameElement);

            Element teamMembersElement = doc.createElement("teamMembers");
            teamMembersElement.appendChild(doc.createTextNode(teamMembers));
            projectElement.appendChild(teamMembersElement);

            root.appendChild(projectElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFunding(String text, String text2, String text3, String text4, String text5) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeFunding'");
    }
}
