package lk.ijse.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.controller.BranchesManagementFormController;
import lk.ijse.dto.BranchDTO;

public class BranchManagementBarController {
    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblContact;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnUpdateSave;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;


    private BranchBO branchBO = new BranchBOImpl();
    private BranchDTO dto;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        BranchesManagementFormController.branchesManagementFormController.dtos.remove(dto);
        BranchesManagementFormController.branchesManagementFormController.loadToTable();
        branchBO.delete(dto);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        txtName.setText(lblName.getText());
        txtAddress.setText(lblAddress.getText());
        txtEmail.setText(lblEmail.getText());

        lblName.setVisible(false);
        lblAddress.setVisible(false);
        lblEmail.setVisible(false);

        txtName.setVisible(true);
        txtAddress.setVisible(true);
        txtEmail.setVisible(true);
        btnUpdateSave.setVisible(true);
        btnUpdate.setVisible(false);
    }

    @FXML
    void btnUpdateSaveOnAction(ActionEvent event) {
        lblName.setText(txtName.getText());
        lblAddress.setText(txtAddress.getText());
        lblEmail.setText(txtEmail.getText());

        lblName.setVisible(true);
        lblAddress.setVisible(true);
        lblEmail.setVisible(true);


        txtName.setVisible(false);
        txtAddress.setVisible(false);
        txtEmail.setVisible(false);
        btnUpdateSave.setVisible(false);
        btnUpdate.setVisible(true);

        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(dto.getId());
        branchDTO.setName(txtName.getText());
        branchDTO.setAddress(lblAddress.getText());
        branchDTO.setEmail(lblEmail.getText());
        boolean isUpdated = branchBO.update(branchDTO);
        if (isUpdated){
        }else{
            new Alert(Alert.AlertType.ERROR , "Error in Updating").show();
        }
    }

    public void setData(BranchDTO dto){
        lblId.setText(String.valueOf(dto.getId()));
        lblName.setText(dto.getName());
        lblAddress.setText(dto.getAddress());
        lblEmail.setText(dto.getEmail());
        this.dto=dto;

    }
}
