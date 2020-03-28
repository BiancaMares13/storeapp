package ro.web.store.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idUser;

    @Column(unique=true)
    @Size(min = 3, max = 15)
    private String username;

    public User(){}
    public User(String username){this.username=username;}

}
