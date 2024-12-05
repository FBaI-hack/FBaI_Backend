package bigtech.fbai.post.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "suspect")
public class Suspect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long suspectId;

    private String name;
    private int count;
    private String email;
    private String bank;
    private String account;
    private String platform;

}
