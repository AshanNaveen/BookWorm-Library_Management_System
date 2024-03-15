package lk.ijse.controller.cardController;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.controller.UserDashboardFormController;
import lk.ijse.dto.BorrowBookDTO;
import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.entity.User;
import lk.ijse.util.DateTimeUtil;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ReturnBookCardController {
    @FXML
    private JFXButton btnReturn;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblDueDate;

    ReturnBookDTO dto;

    @FXML
    void btnReturnOnAction(ActionEvent event) throws IOException {
        UserDashboardFormController.userDashboardFormController.borrowedBooks.remove(dto);
        UserDashboardFormController.userDashboardFormController.loadAllReturns();

    }

    public void setData(ReturnBookDTO dto){
        this.dto=dto;
        this.lblBookName.setText(dto.getBookName());
        this.lblDueDate.setText("Due Date - " + DateTimeUtil.convertDate(DateTimeUtil.getDueTimestamp(dto.getBorrowedTimestamp())));
    }
}
