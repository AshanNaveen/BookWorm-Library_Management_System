package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import lk.ijse.util.Navigation;

public class AdminDashboardFormController {
    @FXML
    private JFXButton btnLogOut;


    @FXML
    void btnBookOnAction(MouseEvent event) {
        Navigation.switchNavigation("books-mangement-form.fxml",event);
    }

    @FXML
    void btnBranchOnAction(MouseEvent event) {
        Navigation.switchNavigation("branches-mangement-form.fxml",event);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Navigation.switchNavigation("login-form.fxml",event);
    }

    @FXML
    void btnUsersNotReturnedOnAction(MouseEvent event) {
        Navigation.switchNavigation("usersNotReturend-form.fxml",event);
    }
}
