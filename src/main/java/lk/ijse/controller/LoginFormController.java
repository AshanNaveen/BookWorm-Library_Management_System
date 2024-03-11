package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import jakarta.persistence.NoResultException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.Objects;

public class LoginFormController {
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPasswordHide;

    @FXML
    private JFXTextField txtPasswordUnHide;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Text btnSignUp;

    @FXML
    private Label lblWarning;

    public static Long userID;

    UserBO userBO = new UserBOImpl();

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            UserDTO userDTO = userBO.findCredential(txtUsername.getText());
            System.out.println(userDTO);

            if (userDTO != null) {
                if (userDTO.getEmail() != null && txtPasswordHide.getText().equals(userDTO.getPassword())) {
                    userID=userDTO.getId();
                    Navigation.switchNavigation("user-dashboard-form.fxml", event);
                }
            } else {
                lblWarning.setText("Invalid Username or Password !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSignUpOnAction(MouseEvent event) {
        try {
            Navigation.switchNavigation("sign-up-form.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
