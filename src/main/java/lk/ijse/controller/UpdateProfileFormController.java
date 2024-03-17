package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.util.Navigation;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class UpdateProfileFormController {
    @FXML
    private Text btnBackWord;

    @FXML
    private ImageView btnBackArrow;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private Pane photoPane;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUploadPhoto;
    private UserBO userBO=new UserBOImpl();

    @FXML
    void btnBackOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation("user-dashboard-form.fxml",event);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            userBO.deleteUser(LoginFormController.userID);
        }
        Navigation.switchNavigation("login-form.fxml",event);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnUploadPhotoOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg","*.gif"));

        File selectedFile = fileChooser.showOpenDialog(btnUploadPhoto.getScene().getWindow());

        if (selectedFile !=null) {

        }
    }
}
