package models;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLAjukanFundingHandler {

    private static final String FILE_PATH = "C:\\Users\\User\\Downloads\\pull\\UnityFund\\TuBes\\src\\data\\funding.xml";

    public static List<AjukanFunding> readFundings() {
        List<AjukanFunding> fundings = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return fundings;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("funding");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String teamName = element.getElementsByTagName("teamName").item(0).getTextContent();
                    String background = element.getElementsByTagName("background").item(0).getTextContent();
                    String teamMembers = element.getElementsByTagName("teamMembers").item(0).getTextContent();
                    String objectives = element.getElementsByTagName("objectives").item(0).getTextContent();
                    String budget = element.getElementsByTagName("budget").item(0).getTextContent();
                    String likes = element.getElementsByTagName("likes").item(0).getTextContent();
                    fundings.add(new AjukanFunding(title, teamName, background, teamMembers, objectives, budget, likes));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fundings;
    }

    public static void writeFunding(String title, String background, String objectives, String teamName, String teamMembers, String budget, String likes) {
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
                Element rootElement = doc.createElement("fundings");
                doc.appendChild(rootElement);
            }

            Element root = doc.getDocumentElement();
            Element fundingElement = doc.createElement("funding");

            Element titleElement = doc.createElement("title");
            titleElement.appendChild(doc.createTextNode(title));
            fundingElement.appendChild(titleElement);

            Element backgroundElement = doc.createElement("background");
            backgroundElement.appendChild(doc.createTextNode(background));
            fundingElement.appendChild(backgroundElement);

            Element objectivesElement = doc.createElement("objectives");
            objectivesElement.appendChild(doc.createTextNode(objectives));
            fundingElement.appendChild(objectivesElement);

            Element teamNameElement = doc.createElement("teamName");
            teamNameElement.appendChild(doc.createTextNode(teamName));
            fundingElement.appendChild(teamNameElement);

            Element teamMembersElement = doc.createElement("teamMembers");
            teamMembersElement.appendChild(doc.createTextNode(teamMembers));
            fundingElement.appendChild(teamMembersElement);

            Element budgetElement = doc.createElement("budget");
            budgetElement.appendChild(doc.createTextNode(budget));
            fundingElement.appendChild(budgetElement);

            Element likesElement = doc.createElement("likes");
            likesElement.appendChild(doc.createTextNode(likes));
            fundingElement.appendChild(likesElement);

            root.appendChild(fundingElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
