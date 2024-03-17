package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.controller.barController.BranchManagementBarController;
import lk.ijse.dto.BranchDTO;
import lk.ijse.util.Navigation;
import lk.ijse.util.ValidateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BranchesManagementFormController {
    @FXML
    private VBox vbox;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXButton btnSave;

    public static BranchesManagementFormController branchesManagementFormController;

    public List<BranchDTO> dtos = new ArrayList<>();
    private BranchBO branchBO=new BranchBOImpl();

    public BranchesManagementFormController(){
        branchesManagementFormController=this;
    }
    public void initialize(){
        List<BranchDTO> branchDTOS = branchBO.loadAll();
        dtos=branchDTOS;
        loadToTable();
    }

    public void loadToTable() {
        vbox.getChildren().clear();
        try{
            for (BranchDTO dto : dtos) {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bar/branchManagementBar.fxml"));
                Parent root=fxmlLoader.load();
                BranchManagementBarController controller = fxmlLoader.getController();
                controller.setData(dto);
                vbox.getChildren().add(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (ValidateUtil.validateMail(txtEmail.getText(),txtEmail)){
            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setName(txtName.getText());
            branchDTO.setAddress(txtAddress.getText());
            branchDTO.setEmail(txtEmail.getText());
            System.out.println(branchDTO);
            branchBO.save(branchDTO);
            dtos.add(branchDTO);
            loadToTable();
        }
    }

    public void btnBackOnAction(MouseEvent mouseEvent) {
        Navigation.switchNavigation("admin-dashboard-form.fxml",mouseEvent);
    }
}
