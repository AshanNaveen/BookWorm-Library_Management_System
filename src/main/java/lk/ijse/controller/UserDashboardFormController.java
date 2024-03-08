package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserDashboardFormController {
    @FXML
    private JFXTextField searchTxt;

    @FXML
    private Pane avaterPane;

    @FXML
    private Label txtName;

    @FXML
    private Label txtMail;

    @FXML
    private Label btnEditProfile;

    @FXML
    private Text lblBorrowedBookCount;

    @FXML
    private JFXButton allBooksBtn;

    @FXML
    private JFXButton novelBtn;

    @FXML
    private JFXButton shortStoriesBtn;

    @FXML
    private JFXButton historyBtn;

    @FXML
    private JFXButton biographyBtn;

    @FXML
    private JFXButton educationalBtn;

    @FXML
    private VBox books;

    @FXML
    private VBox returnVbox;

    @FXML
    private JFXButton btnReadingHistory;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    void branchesOnClicked(MouseEvent event) {

    }

    @FXML
    void btnAllBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnBiographyOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditProfileOnAction(MouseEvent event) {

    }

    @FXML
    void btnEducationalOnAction(ActionEvent event) {

    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnNovelOnAction(ActionEvent event) {

    }

    @FXML
    void btnReadingHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnShortStoriesOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }
}
