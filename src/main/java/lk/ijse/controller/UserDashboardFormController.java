package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lk.ijse.bo.BookBO;
import lk.ijse.bo.BookBOImpl;
import lk.ijse.bo.UserBO;
import lk.ijse.bo.UserBOImpl;
import lk.ijse.controller.cardController.BookCardController;
import lk.ijse.controller.cardController.ReturnBookCardController;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowBookDTO;
import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.Navigation;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDashboardFormController {
    @FXML
    private Pane bodyPane;

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

    public static UserDashboardFormController userDashboardFormController;

    private UserDTO currentUser;

    private UserBO userBO = new UserBOImpl();

    private BookBO bookBO = new BookBOImpl();

    private List<BookDTO> allBooks;

    public List<ReturnBookDTO> borrowedBooks;

    private ActionEvent actionEvent;

    public UserDashboardFormController(){
        userDashboardFormController=this;
    }

    public void initialize() throws IOException {
        System.out.println("Initialize");
        this.currentUser = userBO.getUser(LoginFormController.userID);
        this.txtName.setText(currentUser.getName());
        this.txtMail.setText(currentUser.getEmail());
        this.lblBorrowedBookCount.setText("00");
        allBooksBtn.fire();
        bindAutoCompletation();
        returnVbox.setSpacing(5);
        loadOLDReturnBooks();
        lblBorrowedBookCount.setText(String.valueOf(borrowedBooks.stream().count()));
    }

    private void loadOLDReturnBooks() throws IOException {
        List<ReturnBookDTO> list=bookBO.getNotReturnedBooks(LoginFormController.userID);
        if (list.isEmpty()){
            borrowedBooks=new ArrayList<>();
        }else{
            borrowedBooks=list;
        }
        loadAllReturns();
    }

    public void loadAllReturns() throws IOException {
        returnVbox.getChildren().clear();
        for(ReturnBookDTO dto : borrowedBooks){
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/cards/return-book-card.fxml"));
            Parent root = load.load();
            ReturnBookCardController controller = load.getController();
            controller.setData(new ReturnBookDTO(dto.getPhotoPath(),dto.getBookName(), dto.getBookId(), dto.getAuthor(),dto.getUserId(),dto.getBorrowedTimestamp()));
            returnVbox.getChildren().add(root);
        }
    }
    private void bindAutoCompletation() {
        List<String> list = allBooks.stream().map(bookDTO -> bookDTO.getName()).toList();
        TextFields.bindAutoCompletion(searchTxt, list);
    }

    public void loadAllBooks() {
        books.getChildren().clear();
        try {
            List<BookDTO> bookDTOS = bookBO.loadAllBooks();
            allBooks = bookDTOS;
            HBox hBox = createHbox();

            for (BookDTO bookDTO : bookDTOS) {
                if (hBox.getChildren().stream().count() < 4) {
                    hBox = setToBooks(bookDTO, hBox);
                    System.out.println(bookDTO);
                    System.out.println(hBox.getChildren().stream().count());
                } else {
                    books.getChildren().add(hBox);
                    hBox = createHbox();
                    System.out.println("New");
                    System.out.println(bookDTO);
                    hBox = setToBooks(bookDTO, hBox);
                }
            }
            books.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HBox createHbox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(199.00);
        hBox.setSpacing(5);
        return hBox;
    }

    private HBox setToBooks(BookDTO bookDTO, HBox hBox) throws IOException {
        BorrowBookDTO borrowBookDTO = new BorrowBookDTO(bookDTO.getPhotoPath(), bookDTO.getName(), bookDTO.getId(), bookDTO.getAuthor(), currentUser.getId(), returnVbox,lblBorrowedBookCount,null);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/cards/book-card.fxml"));
        Parent load = fxmlLoader.load();
        BookCardController controller = fxmlLoader.getController();
        controller.setData(borrowBookDTO);
        hBox.getChildren().add(load);
        return hBox;
    }

    @FXML
    void branchesOnClicked(MouseEvent event) throws IOException {
        Navigation.switchNavigation("branches-form.fxml",event);
    }

    @FXML
    void btnAllBooksOnAction(ActionEvent event) {
        loadAllBooks();
        if (actionEvent == null) {
            actionEvent = event;
            allBooksBtn.getStyleClass().add("active-genreBtn");
        } else {
            JFXButton source = (JFXButton) actionEvent.getSource();
            JFXButton thisBtn = (JFXButton) event.getSource();
            if (!source.getId().equals(thisBtn.getId())) {
                source.getStyleClass().remove("active-genreBtn");
                source.getStyleClass().add("genreBtn");
                thisBtn.getStyleClass().remove("genreBtn");
                thisBtn.getStyleClass().add("active-genreBtn");
                actionEvent = event;
            }
        }
    }

    @FXML
    void btnBiographyOnAction(ActionEvent event) {
        loadBiographyBooks();
        JFXButton source = (JFXButton) actionEvent.getSource();
        JFXButton thisBtn = (JFXButton) event.getSource();
        if (!source.getId().equals(thisBtn.getId())) {
            source.getStyleClass().remove("active-genreBtn");
            source.getStyleClass().add("genreBtn");
            thisBtn.getStyleClass().remove("genreBtn");
            thisBtn.getStyleClass().add("active-genreBtn");
            actionEvent = event;
        }
    }

    private void loadBiographyBooks() {
        books.getChildren().clear();
        try {
            HBox hBox = createHbox();

            for (BookDTO bookDTO : allBooks) {
                if (bookDTO.getGenre().equals("Biography")) {
                    if (hBox.getChildren().stream().count() < 4) {
                        hBox = setToBooks(bookDTO, hBox);
                        System.out.println(bookDTO);
                        System.out.println(hBox.getChildren().stream().count());
                    } else {
                        books.getChildren().add(hBox);
                        hBox = createHbox();
                        System.out.println("New");
                        System.out.println(bookDTO);
                        hBox = setToBooks(bookDTO, hBox);
                    }
                }
            }
            books.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEditProfileOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation("updateProfile-form.fxml",event);
    }

    @FXML
    void btnEducationalOnAction(ActionEvent event) {
        loadEducationalBooks();
        JFXButton source = (JFXButton) actionEvent.getSource();
        JFXButton thisBtn = (JFXButton) event.getSource();
        if (!source.getId().equals(thisBtn.getId())) {
            source.getStyleClass().remove("active-genreBtn");
            source.getStyleClass().add("genreBtn");
            thisBtn.getStyleClass().remove("genreBtn");
            thisBtn.getStyleClass().add("active-genreBtn");
            actionEvent = event;
        }
    }

    private void loadEducationalBooks() {
        books.getChildren().clear();
        try {
            HBox hBox = createHbox();

            for (BookDTO bookDTO : allBooks) {
                if (bookDTO.getGenre().equals("Educational")) {
                    if (hBox.getChildren().stream().count() < 4) {
                        hBox = setToBooks(bookDTO, hBox);
                        System.out.println(bookDTO);
                        System.out.println(hBox.getChildren().stream().count());
                    } else {
                        books.getChildren().add(hBox);
                        hBox = createHbox();
                        System.out.println("New");
                        System.out.println(bookDTO);
                        hBox = setToBooks(bookDTO, hBox);
                    }
                }
            }
            books.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) {
        loadHistoryBooks();
        JFXButton source = (JFXButton) actionEvent.getSource();
        JFXButton thisBtn = (JFXButton) event.getSource();
        if (!source.getId().equals(thisBtn.getId())) {
            source.getStyleClass().remove("active-genreBtn");
            source.getStyleClass().add("genreBtn");
            thisBtn.getStyleClass().remove("genreBtn");
            thisBtn.getStyleClass().add("active-genreBtn");
            actionEvent = event;
        }
    }

    private void loadHistoryBooks() {
        books.getChildren().clear();
        try {
            HBox hBox = createHbox();

            for (BookDTO bookDTO : allBooks) {
                if (bookDTO.getGenre().equals("History")) {
                    if (hBox.getChildren().stream().count() < 4) {
                        hBox = setToBooks(bookDTO, hBox);
                    } else {
                        books.getChildren().add(hBox);
                        hBox = createHbox();
                        hBox = setToBooks(bookDTO, hBox);
                    }
                }
            }
            books.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Navigation.switchNavigation("login-form.fxml",event);
    }

    @FXML
    void btnNovelOnAction(ActionEvent event) {
        loadNovelBooks();
        JFXButton source = (JFXButton) actionEvent.getSource();
        JFXButton thisBtn = (JFXButton) event.getSource();
        if (!source.getId().equals(thisBtn.getId())) {
            source.getStyleClass().remove("active-genreBtn");
            source.getStyleClass().add("genreBtn");
            thisBtn.getStyleClass().remove("genreBtn");
            thisBtn.getStyleClass().add("active-genreBtn");
            actionEvent = event;
        }
    }

    private void loadNovelBooks() {
        books.getChildren().clear();
        try {
            HBox hBox = createHbox();

            for (BookDTO bookDTO : allBooks) {
                if (bookDTO.getGenre().equals("Novel")) {
                    if (hBox.getChildren().stream().count() < 4) {
                        hBox = setToBooks(bookDTO, hBox);
                        System.out.println(bookDTO);
                        System.out.println(hBox.getChildren().stream().count());
                    } else {
                        books.getChildren().add(hBox);
                        hBox = createHbox();
                        System.out.println("New");
                        System.out.println(bookDTO);
                        hBox = setToBooks(bookDTO, hBox);
                    }
                }
            }
            books.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnReadingHistoryOnAction(ActionEvent event) {
        Navigation.switchNavigation("reading-history.fxml",event);
    }

    @FXML
    void btnShortStoriesOnAction(ActionEvent event) {
        loadShotStories();
        JFXButton source = (JFXButton) actionEvent.getSource();
        JFXButton thisBtn = (JFXButton) event.getSource();
        if (!source.getId().equals(thisBtn.getId())) {
            source.getStyleClass().remove("active-genreBtn");
            source.getStyleClass().add("genreBtn");
            thisBtn.getStyleClass().remove("genreBtn");
            thisBtn.getStyleClass().add("active-genreBtn");
            actionEvent = event;
        }
    }

    private void loadShotStories() {
        books.getChildren().clear();
        try {
            HBox hBox = createHbox();

            for (BookDTO bookDTO : allBooks) {
                if (bookDTO.getGenre().equals("Short Stories")) {
                    if (hBox.getChildren().stream().count() < 4) {
                        hBox = setToBooks(bookDTO, hBox);
                        System.out.println(bookDTO);
                        System.out.println(hBox.getChildren().stream().count());
                    } else {
                        books.getChildren().add(hBox);
                        hBox = createHbox();
                        System.out.println("New");
                        System.out.println(bookDTO);
                        hBox = setToBooks(bookDTO, hBox);
                    }
                }
            }
            books.getChildren().add(hBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String bookName = searchTxt.getText();
        List<BookDTO> filteredBooks = allBooks.stream()
                .filter(bookDTO -> bookDTO.getName().equals(bookName))
                .collect(Collectors.toList());

        books.getChildren().clear();
        HBox hBox = createHbox();

        try {
            for (BookDTO bookDTO : filteredBooks) {
                if (hBox.getChildren().stream().count() < 4) {
                    hBox = setToBooks(bookDTO, hBox);
                    System.out.println(bookDTO);
                    System.out.println(hBox.getChildren().stream().count());
                } else {
                    books.getChildren().add(hBox);
                    hBox = createHbox();
                    System.out.println("New");
                    System.out.println(bookDTO);
                    hBox = setToBooks(bookDTO, hBox);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        books.getChildren().add(hBox);
        JFXButton source = (JFXButton) actionEvent.getSource();
        source.getStyleClass().remove("active-genreBtn");
        source.getStyleClass().add("genreBtn");

    }
}
