package lk.ijse.controller.barController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.controller.BooksManagementFormController;
import lk.ijse.dto.BookDTO;

public class BookBarController {
    @FXML
    private Text txtId;

    @FXML
    private Text txtAuthor;

    @FXML
    private Text txtGenre;

    @FXML
    private Text txtIsbn;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Text txtName;

    @FXML
    private Label txtPhotoPath;

    @FXML
    private JFXTextField txtEName;

    @FXML
    private JFXTextField txtEAuthor;

    @FXML
    private JFXTextField txtEIsbn;

    @FXML
    private JFXTextField txtEPhotoPath;

    @FXML
    private JFXButton btnUpdateSave;

    @FXML
    private JFXComboBox<?> cmbGenre;
    private BookDTO dto;

    BookBO bookBO = new BookBOImpl();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        System.out.println(dto);
        BooksManagementFormController.booksManagementFormController.tbl.remove(dto);
        BooksManagementFormController.booksManagementFormController.loadBookstbl();
        bookBO.delete(dto.getId());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        txtEName.setText(txtName.getText());
        txtEAuthor.setText(txtAuthor.getText());
        txtEIsbn.setText(txtIsbn.getText());
        txtEPhotoPath.setText(txtPhotoPath.getText());

        txtName.setVisible(false);
        txtAuthor.setVisible(false);
        txtGenre.setVisible(false);
        txtIsbn.setVisible(false);
        txtPhotoPath.setVisible(false);

        txtEName.setVisible(true);
        txtEAuthor.setVisible(true);
        txtEIsbn.setVisible(true);
        txtEPhotoPath.setVisible(true);
        cmbGenre.setVisible(true);
        btnUpdateSave.setVisible(true);
        btnUpdate.setVisible(false);
    }

    @FXML
    void btnUpdateSaveOnAction(ActionEvent event) {
        txtName.setText(txtEName.getText());
        txtAuthor.setText(txtEAuthor.getText());
        txtIsbn.setText(txtEIsbn.getText());
        txtPhotoPath.setText(txtEPhotoPath.getText());

        txtName.setVisible(true);
        txtAuthor.setVisible(true);
        txtGenre.setVisible(true);
        txtIsbn.setVisible(true);
        txtPhotoPath.setVisible(true);


        txtEName.setVisible(false);
        txtEAuthor.setVisible(false);
        txtEIsbn.setVisible(false);
        txtEPhotoPath.setVisible(false);
        cmbGenre.setVisible(false);
        btnUpdateSave.setVisible(false);
        btnUpdate.setVisible(true);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(dto.getId());
        bookDTO.setName(txtName.getText());
        bookDTO.setIsbn(txtIsbn.getText());
        boolean isUpdated = bookBO.update(bookDTO);
        if (isUpdated){
        }else{
            new Alert(Alert.AlertType.ERROR , "Error in Updating").show();
        }
    }

    public void setData(BookDTO dto) {
        txtId.setText(String.valueOf(dto.getId()));
        txtName.setText(dto.getName());
        txtAuthor.setText(dto.getAuthor());
        txtGenre.setText(dto.getGenre());
        txtIsbn.setText(dto.getIsbn());
        txtPhotoPath.setText(dto.getPhotoPath());

        this.dto = dto;
    }
}
