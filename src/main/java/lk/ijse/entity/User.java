package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "user")
@Data
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

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "userId")
    private List<Branch> brances;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
