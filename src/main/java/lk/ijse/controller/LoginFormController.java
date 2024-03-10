package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.bo.UserBO;
import lk.ijse.bo.UserBOImpl;
import lk.ijse.dto.UserDTO;

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

    UserBO userBO = new UserBOImpl();

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        UserDTO userDTO = userBO.findCredential(txtUsername.getText());
        System.out.println(userDTO);

        if (userDTO!=null){
            if (userDTO.getGmail()!=null && textPassword.getText().equals(userDTO.getPassword())){
                Parent root = null;
                root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashbord-form.fxml")));
                if (root != null) {
                    Scene subScene = new Scene(root);
                    Stage primaryStage = (Stage) this.root.getScene().getWindow();
                    primaryStage.setScene(subScene);
                    primaryStage.centerOnScreen();

                    TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                    tt.setFromX(-subScene.getWidth());
                    tt.setToX(0);
                    tt.play();
                }
            }
        }else {
            lblWarning.setText("empty value or Invalid UserNAme or Password !");
        }
    }

    @FXML
    void btnSignUpOnAction(MouseEvent event) {

    }
}
