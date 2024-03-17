package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BorrowBookDTO;

public interface BorrowBookBO extends SuperBO {
    void borrowBook(BorrowBookDTO dto);

    boolean updateReturnedBook(Long userId, Long bookId);
}
