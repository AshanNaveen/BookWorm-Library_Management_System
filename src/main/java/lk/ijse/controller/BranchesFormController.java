package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.controller.barController.BranchBarController;
import lk.ijse.dto.BranchDTO;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.util.List;


public class BranchesFormController {
    @FXML
    private VBox vbox;

    private BranchBO branchBO = new BranchBOImpl();

    public void initialize() throws IOException {
        loadAllBranches();
    }

    private void loadAllBranches() throws IOException {
        vbox.getChildren().clear();
        List<BranchDTO> branchDTOList = branchBO.loadAll();
        for (BranchDTO branchDTO : branchDTOList) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bar/branchBar.fxml"));
            Parent load = fxmlLoader.load();
            BranchBarController controller = fxmlLoader.getController();
            controller.setData(branchDTO);
            vbox.getChildren().add(load);
        }
    }


    @FXML
    void btnBackOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation("user-dashboard-form.fxml",event);
    }
}
