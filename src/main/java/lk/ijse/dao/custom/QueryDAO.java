package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.ReturnBookDTO;
import lk.ijse.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<Book> getAllBooksFromUser(Long userId);

    List<ReturnBookDTO> getNotReturnedBooksByUser(Long userId);

}
