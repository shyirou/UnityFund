package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class KotakController {

    @FXML
    private AnchorPane Kotak;

    @FXML
    private ImageView imageKotak;

    @FXML
    private Label judulProject;

    public void setJudulProject(String judul) {
        this.judulProject.setText(judul);
    }

    public void setImage(Image image) {
        this.imageKotak.setImage(image);
    }
}
