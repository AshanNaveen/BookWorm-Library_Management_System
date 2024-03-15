package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import lk.ijse.util.Navigation;

import java.io.IOException;


public class BranchesFormController {
    @FXML
    private ScrollPane vbox;


    public void initialize(){
        loadAllBranches();
    }

    private void loadAllBranches() {

    }


    @FXML
    void btnBackOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation("user-dashboard-form.fxml",event);
    }
}
