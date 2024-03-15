package lk.ijse.bo;

import lk.ijse.dto.UserDTO;

public interface UserBO {
    UserDTO findCredential(String text);
    Boolean saveUser(UserDTO userDTO);
    UserDTO getUser(Long userID);

    void deleteUser(Long userId);
}
