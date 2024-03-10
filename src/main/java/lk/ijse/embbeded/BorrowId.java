package lk.ijse.embbeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BorrowId implements Serializable {
    @Column(name = "bookId")
    private Long bookId;
    @Column(name = "userId")
    private Long userId;
}
