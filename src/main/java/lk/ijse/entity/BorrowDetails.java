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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "dueTimestamp")
    private Timestamp dueTimestamp;

    @Column(name = "isReturned")
    private boolean isReturned;
}
