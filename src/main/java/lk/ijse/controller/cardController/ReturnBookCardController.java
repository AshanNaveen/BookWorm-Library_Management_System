package lk.ijse.controller.cardController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.bo.custom.impl.BorrowBookBOImpl;
import lk.ijse.controller.UserDashboardFormController;
import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.util.DateTimeUtil;

import java.io.IOException;

public class ReturnBookCardController {
    @FXML
    private JFXButton btnReturn;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblDueDate;

    ReturnBookDTO dto;

    BorrowBookBOImpl borrowBookBO = new BorrowBookBOImpl();

    @FXML
    void btnReturnOnAction(ActionEvent event) throws IOException {
        System.out.println("return");
        UserDashboardFormController.userDashboardFormController.borrowedBooks.remove(dto);
        UserDashboardFormController.userDashboardFormController.loadAllReturns();
        borrowBookBO.updateReturnedBook(dto.getUserId(),dto.getBookId());
        UserDashboardFormController.userDashboardFormController.loadAllBooks();
    }

    public void setData(ReturnBookDTO dto){
        this.dto=dto;
        this.lblBookName.setText(dto.getBookName());
        this.lblDueDate.setText("Due Date - " + DateTimeUtil.convertDate(DateTimeUtil.getDueTimestamp(dto.getBorrowedTimestamp())));
    }
}
