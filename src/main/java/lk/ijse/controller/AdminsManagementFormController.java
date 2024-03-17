package lk.ijse.controller;

import javafx.scene.input.MouseEvent;
import lk.ijse.util.Navigation;

public class AdminsManagementFormController {
    public void btnBackOnAction(MouseEvent mouseEvent) {
        Navigation.switchNavigation("admin-dashboard-form.fxml",mouseEvent);
    }
}
