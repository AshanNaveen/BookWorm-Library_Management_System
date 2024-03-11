package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.bo.UserBO;
import lk.ijse.bo.UserBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class SignUpFormController {
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXPasswordField txtPasswordHide;

    @FXML
    private JFXTextField txtPasswordUnHide;

    @FXML
    private Text btnSignIn;

    @FXML
    private Label lblWarning;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    UserBO userBO = new UserBOImpl();
    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (!(txtUsername.getText().isEmpty() && txtPasswordHide.getText().isEmpty() && txtEmail.getText().isEmpty() && txtPhone.getText().isEmpty())){
            UserDTO userDTO = new UserDTO(0L, txtUsername.getText(), txtEmail.getText(), txtPhone.getText(), txtPasswordHide.getText(), null, false);
            Boolean isSaved=userBO.saveUser(userDTO);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR).show();
            }
        }
    }

    @FXML
    void btnRegisterOnEnterd(MouseEvent event) {

    }

    @FXML
    void btnRegisterOnExited(MouseEvent event) {

    }

    @FXML
    void btnSignInOnAction(MouseEvent event) {
        try {
//            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml")));
//            Stage window = (Stage) btnRegister.getScene().getWindow();
//            window.setScene(scene);
            Navigation.switchNavigation("login-form.fxml",event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
