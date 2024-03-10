package lk.ijse.entity;

import jakarta.persistence.*;

import lk.ijse.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "email" , nullable = false , unique = true)
    private String email;
    @Column(name = "phone" , nullable = false )
    private String phone;
    @Column(name = "password" , nullable = false)
    private String password;
    @Column(name = "photoPath")
    private String photoPath;
    @Column(name = "isAdmin" , nullable = false)
    private boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "user")
    private List<Branch> brances;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "user")
    private List<BorrowDetails> borrowDetails;

    public UserDTO toDTO(){
        return new UserDTO(id,name,email,phone,password,photoPath,isAdmin);
    }
}
