package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.XMLAjukanFundingHandler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import views.ViewFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class ProSub1Controller {

    @FXML
    private Button Collab;

    @FXML
    private Button Funding;

    @FXML
    private Button LogOut;

    @FXML
    private AnchorPane ProSub;

    @FXML
    private Button Project;

    @FXML
    private Button ProjectSubmission;

    @FXML
    private TextField anggotaTeam;

    @FXML
    private TextField judulProject;

    @FXML
    private TextField latarBelakang;

    @FXML
    private TextField namaTeam;

    @FXML
    private TextField tujuanKegiatan;

    ViewFactory m = new ViewFactory();

    @FXML
    private Label lbnamaProfile;

    @FXML
    private Button btUpload;

    @FXML
    private Button iduploadImage;

    private File selectedImageFile;
    private static final int MAX_PROJECTS = 2;

    @FXML
    void LogOut(ActionEvent event) {
        Stage stage = (Stage) ProSub.getScene().getWindow();
        m.nextPagetoLogin(stage);
    }

    @FXML
    void klikCollab(ActionEvent event) {
        Stage stage = (Stage) ProSub.getScene().getWindow();
        m.nextPageCollab(stage);
    }

    @FXML
    void klikFunding(ActionEvent event) {
        Stage stage = (Stage) ProSub.getScene().getWindow();
        m.nextPageFunding(stage);
    }

    @FXML
    void klikProject(ActionEvent event) {
        Stage stage = (Stage) ProSub.getScene().getWindow();
        m.nextPageHome(stage);
    }

    @FXML
    void klikSub(ActionEvent event) {
        // Metode untuk pengiriman proyek
    }

    @FXML
    void clickUpload(ActionEvent event) {
        try {
            // File XML yang akan disimpan
            File xmlFile = new File("C:\\Users\\User\\Downloads\\pull\\UnityFund\\TuBes\\src\\data\\projects.xml");

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document;

            // Cek jika file sudah ada
            if (xmlFile.exists()) {
                document = documentBuilder.parse(xmlFile);
                document.getDocumentElement().normalize();
            } else {
                document = documentBuilder.newDocument();
                Element root = document.createElement("projects");
                document.appendChild(root);
            }

            // Hitung jumlah proyek saat ini
            NodeList projectList = document.getElementsByTagName("project");
            if (projectList.getLength() >= MAX_PROJECTS) {
                showAlert("Maksimal Proyek Tercapai", "Anda hanya dapat menginput maksimal " + MAX_PROJECTS + " proyek.");
                return;
            }

            // Buat elemen proyek baru
            Element project = document.createElement("project");

            Element judulProjectElement = document.createElement("judulProject");
            judulProjectElement.appendChild(document.createTextNode(judulProject.getText()));
            project.appendChild(judulProjectElement);

            Element latarBelakangElement = document.createElement("latarBelakang");
            latarBelakangElement.appendChild(document.createTextNode(latarBelakang.getText()));
            project.appendChild(latarBelakangElement);

            Element tujuanKegiatanElement = document.createElement("tujuanKegiatan");
            tujuanKegiatanElement.appendChild(document.createTextNode(tujuanKegiatan.getText()));
            project.appendChild(tujuanKegiatanElement);

            Element namaTeamElement = document.createElement("namaTeam");
            namaTeamElement.appendChild(document.createTextNode(namaTeam.getText()));
            project.appendChild(namaTeamElement);

            Element anggotaTeamElement = document.createElement("anggotaTeam");
            String[] anggotaList = anggotaTeam.getText().split(",");
            for (String anggota : anggotaList) {
                Element anggotaElement = document.createElement("anggota");
                anggotaElement.appendChild(document.createTextNode(anggota.trim()));
                anggotaTeamElement.appendChild(anggotaElement);
            }
            project.appendChild(anggotaTeamElement);

            // Tambahkan elemen gambar jika ada
            if (selectedImageFile != null) {
                Element imageElement = document.createElement("image");
                imageElement.appendChild(document.createTextNode("uploaded_images/" + selectedImageFile.getName()));
                project.appendChild(imageElement);

                // Tentukan lokasi penyimpanan gambar
                Path targetLocation = new File("uploaded_images/" + selectedImageFile.getName()).toPath();
                // Buat direktori jika belum ada
                Files.createDirectories(targetLocation.getParent());
                // Salin file gambar yang dipilih ke lokasi tujuan
                Files.copy(selectedImageFile.toPath(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image berhasil diunggah: " + targetLocation.toString());
            }

            // Tambahkan proyek baru ke root
            document.getDocumentElement().appendChild(project);

            // Tulis ke file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(xmlFile);
            transformer.transform(domSource, streamResult);

            // Tampilkan alert sukses
            showAlert("Upload Successful", "Data proyek dan gambar berhasil diunggah!");

            // Hapus konten dari TextField setelah berhasil mengunggah
            clearFields();

        } catch (ParserConfigurationException | TransformerException | org.xml.sax.SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        selectedImageFile = fileChooser.showOpenDialog(ProSub.getScene().getWindow());

        if (selectedImageFile != null) {
            // Tampilkan alert sukses memilih gambar
            showAlert("Image Selected", "Gambar berhasil dipilih: " + selectedImageFile.getName());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        namaTeam.clear();
        judulProject.clear();
        latarBelakang.clear();
        anggotaTeam.clear();
        tujuanKegiatan.clear();
    }
}
