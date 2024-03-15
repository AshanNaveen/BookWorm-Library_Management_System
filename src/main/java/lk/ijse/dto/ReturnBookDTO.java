package lk.ijse.dto;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBookDTO {
    private String photoPath;
    private String bookName;
    private Long bookId;
    private String author;
    private Long userId;
    private Timestamp borrowedTimestamp;
}
