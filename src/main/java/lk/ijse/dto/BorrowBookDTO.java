package lk.ijse.dto;

import javafx.scene.layout.VBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookDTO {
    private String photoPath;
    private String bookName;
    private String bookId;
    private String author;
    private Long userId;
    private VBox returnBox;
}
