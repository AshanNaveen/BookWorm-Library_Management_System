package lk.ijse.dao;

import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO {

    List<Book> getAllBooksFromUser(Long userId);

    List<ReturnBookDTO> getNotReturnedBooksByUser(Long userId);

    void setSession(Session session);
}
