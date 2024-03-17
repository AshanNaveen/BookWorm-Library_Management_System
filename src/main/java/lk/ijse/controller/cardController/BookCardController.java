package lk.ijse.controller.cardController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.bo.custom.BorrowBookBO;
import lk.ijse.bo.custom.impl.BorrowBookBOImpl;
import lk.ijse.controller.UserDashboardFormController;
import lk.ijse.dto.BorrowBookDTO;
import lk.ijse.dto.ReturnBookDTO;

import java.io.IOException;
import java.sql.Timestamp;

import static lk.ijse.util.DateTimeUtil.getDueTimestamp;

public class BookCardController {
    @FXML
    private ImageView imgBook;

    @FXML
    private Label txtBookName;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private Label txtWriterName;

    private BorrowBookDTO dto;


    BorrowBookBO borrowBookBO = new BorrowBookBOImpl();


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp newTimestamp = getDueTimestamp(timestamp);

        dto.setBorrowedTimestamp(timestamp);

        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/cards/return-book-card.fxml"));
        Parent root = load.load();
        ReturnBookCardController controller = load.getController();
        controller.setData(new ReturnBookDTO(dto.getPhotoPath(), dto.getBookName(),dto.getBookId(),dto.getAuthor(),dto.getUserId(),dto.getBorrowedTimestamp()));
        dto.getReturnBox().getChildren().add(root);

        UserDashboardFormController.userDashboardFormController.borrowedBooks.add(new ReturnBookDTO(dto.getPhotoPath(), dto.getBookName(),dto.getBookId(),dto.getAuthor(),dto.getUserId(),dto.getBorrowedTimestamp()));

        int count = Integer.parseInt(dto.getLblCount().getText());
        dto.getLblCount().setText(((count+1)<10 ? "0"+(count+1) : ""+(count+1)));

        borrowBook();
        UserDashboardFormController.userDashboardFormController.loadAllBooks();
    }

    private void borrowBook() {
        borrowBookBO.borrowBook(dto);
    }


    public void setData(BorrowBookDTO dto){
        this.dto=dto;
        imgBook.setFitHeight(115);
        imgBook.setFitWidth(112);
        imgBook.setImage(new Image(dto.getPhotoPath()));
        txtBookName.setText(dto.getBookName());
        txtWriterName.setText("By "+dto.getAuthor());
    }
}
