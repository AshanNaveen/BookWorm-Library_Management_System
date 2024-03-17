package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

public interface UserBO extends SuperBO {
    UserDTO findCredential(String text);
    Boolean saveUser(UserDTO userDTO);
    UserDTO getUser(Long userID);

    void deleteUser(Long userId);
}
