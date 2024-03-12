package lk.ijse.controller.cardController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BorrowBookDTO;
import lombok.Setter;

public class BookCardController {
    @FXML
    private ImageView imgBook;

    @FXML
    private Label txtBookName;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private Label txtWriterName;

    @Setter
    private BorrowBookDTO dto;

    public void initialize(){
        imgBook.setImage(new Image(dto.getPhotoPath()));
        txtBookName.setText(dto.getBookName());
        txtWriterName.setText("By "+dto.getAuthor());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }
}
