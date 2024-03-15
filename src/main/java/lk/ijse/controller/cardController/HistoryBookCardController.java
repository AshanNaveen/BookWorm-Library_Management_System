package lk.ijse.controller.cardController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BookDTO;

public class HistoryBookCardController {
    @FXML
    private ImageView imgBook;

    @FXML
    private Label txtBookName;

    @FXML
    private Label txtWriterName;
    public void setData(BookDTO bookDTO) {
        imgBook.setImage(new Image(bookDTO.getPhotoPath()));
        txtBookName.setText(bookDTO.getName());
        txtWriterName.setText(bookDTO.getAuthor());
    }
}
