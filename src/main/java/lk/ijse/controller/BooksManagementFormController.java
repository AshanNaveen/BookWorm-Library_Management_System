package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.controller.barController.BookBarController;
import lk.ijse.dto.BookDTO;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksManagementFormController {


    @FXML
    private VBox vBox;
    @FXML
    private JFXTextField txtEName;

    @FXML
    private JFXTextField txtEEmail;

    @FXML
    private JFXTextField txtEAddress;

    @FXML
    private JFXTextField txtEPhone;

    @FXML
    private JFXTextField txtENic;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtIsbn;

    @FXML
    private JFXComboBox<?> cmbGenre;

    public static BooksManagementFormController booksManagementFormController;

    BookBO bookBO = new BookBOImpl();
    public List<BookDTO> allbooks;
    public List<BookDTO> tbl;

    public BooksManagementFormController(){
        tbl=new ArrayList<>();
        booksManagementFormController=this;
    }
    public void initialize(){
        allbooks= bookBO.loadAllBooks();
        loadBooks();
        loadCmbBox();
    }

    private void loadCmbBox() {
        ObservableList<String> genres= FXCollections.observableArrayList();

    }

    public void loadBooks()  {
        vBox.getChildren().clear();
        try {
            for (BookDTO bookDTO : allbooks) {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bar/bookBar.fxml"));
                Parent load = fxmlLoader.load();
                BookBarController controller = fxmlLoader.getController();
                controller.setData(bookDTO);
                tbl.add(bookDTO);
                vBox.getChildren().add(load);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadBookstbl()  {
        vBox.getChildren().clear();
        try {
            for (BookDTO bookDTO : tbl) {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/bar/bookBar.fxml"));
                Parent load = fxmlLoader.load();
                BookBarController controller = fxmlLoader.getController();
                controller.setData(bookDTO);
                vBox.getChildren().add(load);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnBackOnAction(MouseEvent event) {
        Navigation.switchNavigation("admin-dashboard-form.fxml",event);
    }

    @FXML
    void btnChoosePhotoOnAction(ActionEvent event) {

    }

    @FXML
    void txtSaveOnAction(ActionEvent event) {

    }

}
