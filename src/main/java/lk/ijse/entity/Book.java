package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "genre" , nullable = false)
    private String genre;
    @Column(name = "author" , nullable = false)
    private String author;
    @Column(name = "photoPath" , nullable = false)
    private String photoPath;
    @Column(name = "isbn" ,nullable = false,unique = true)
    private String isbn;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "book")
    private List<BorrowDetails> borrowDetails;

    public BookDTO toDTO(){
        return new BookDTO(id,name,genre,author,photoPath,isbn);
    }
}
