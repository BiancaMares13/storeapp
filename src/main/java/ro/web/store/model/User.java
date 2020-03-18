package ro.web.store.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Data
@Entity
public class User {

    @Id
    private long idUser;

    @Column(unique=true)
    @Size(min = 3, max = 15)
    private String username;
}
