package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.embbeded.BorrowId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "borrow_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
