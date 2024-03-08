package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignUpOnAction(MouseEvent event) {

    }
}
