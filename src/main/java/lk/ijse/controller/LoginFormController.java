package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.Navigation;

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
                    userID = userDTO.getId();
                    if (userDTO.isAdmin()) {
                        Navigation.switchNavigation("admin-dashboard-form.fxml", event);
                    } else {
                        Navigation.switchNavigation("user-dashboard-form.fxml", event);
                    }
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
        Navigation.switchNavigation("sign-up-form.fxml", event);

    }
}
