package lk.ijse.bo;

import lk.ijse.dto.BookDTO;
import lk.ijse.dto.ReturnBookDTO;

import java.util.List;

public interface BookBO {
    List<BookDTO> loadAllBooks();

    List<BookDTO> loadAllBooksByUser(Long userID);

    List<ReturnBookDTO> getNotReturnedBooks(Long userID);

    void delete(Long id);

    boolean update(BookDTO bookDTO);
}
