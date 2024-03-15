package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.bo.BookBO;
import lk.ijse.bo.BookBOImpl;
import lk.ijse.controller.cardController.BookCardController;
import lk.ijse.controller.cardController.HistoryBookCardController;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowBookDTO;
import lk.ijse.entity.Book;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.util.List;

public class ReadingHistoryController {
    @FXML
    private VBox books;

    @FXML
    private Text btnBackWord;

    @FXML
    private ImageView btnBackArrow;

    BookBO bookBO = new BookBOImpl();

    public void initialize() {
        loadBooks();
    }

    private void loadBooks() {

        books.getChildren().clear();
        try {
            List<BookDTO> all = bookBO.loadAllBooksByUser(LoginFormController.userID);

            HBox hBox = createHbox();

            for (BookDTO bookDTO : all) {
                if (hBox.getChildren().stream().count() < 5) {
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

    @FXML
    void btnBackOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation("user-dashboard-form.fxml", event);
    }

    private HBox createHbox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(172.00);
        hBox.setSpacing(5);
        return hBox;
    }

    private HBox setToBooks(BookDTO bookDTO, HBox hBox) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/cards/history-book-card.fxml"));
        Parent load = fxmlLoader.load();
        HistoryBookCardController controller = fxmlLoader.getController();
        controller.setData(bookDTO);
        hBox.getChildren().add(load);
        return hBox;
    }
}
