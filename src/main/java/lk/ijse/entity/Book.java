package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "book")
@Data
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", ISBN='" + isbn + '\'' +
                '}';
    }
}
