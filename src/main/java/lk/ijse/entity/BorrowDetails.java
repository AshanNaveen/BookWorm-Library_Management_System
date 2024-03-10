package lk.ijse.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.embbeded.BorrowId;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity(name = "borrow_details")
@Data
public class BorrowDetails {
    @EmbeddedId
    private BorrowId borrowId;
    @ManyToOne
    @JoinColumn(name = "uId",insertable = false,updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "bId",insertable = false,updatable = false)
    private Book book;
    @CreationTimestamp
    private Timestamp timestamp;
}
