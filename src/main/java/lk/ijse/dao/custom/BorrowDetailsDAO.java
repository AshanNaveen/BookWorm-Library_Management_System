package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowDetails;
import lk.ijse.entity.User;

public interface BorrowDetailsDAO extends SuperDAO {
    Long save(BorrowDetails object);

    BorrowDetails getNotReurnedBookByUserAndBook(User userId, Book bookId);

    void update(BorrowDetails borrowDetails);
}
