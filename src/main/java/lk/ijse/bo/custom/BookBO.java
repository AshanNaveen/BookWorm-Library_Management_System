package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.ReturnBookDTO;

import java.util.List;

public interface BookBO extends SuperBO {
    List<BookDTO> loadAllBooks();

    List<BookDTO> loadAllBooksByUser(Long userID);

    List<ReturnBookDTO> getNotReturnedBooks(Long userID);

    void delete(Long id);

    boolean update(BookDTO bookDTO);
}
