package lk.ijse.controller.barController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.dto.BranchDTO;

public class BranchBarController {
    @FXML
    private Label lblName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblEmail;


    public void setData(BranchDTO dto){
        lblAddress.setText(dto.getAddress());
        lblEmail.setText(dto.getEmail());
        lblName.setText(dto.getName());
    }
}
