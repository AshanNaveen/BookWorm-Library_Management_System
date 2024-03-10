package lk.ijse.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cache;

@Entity(name = "branch")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "email" , nullable = false)
    private String email;
    @Column(name = "address" , nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
