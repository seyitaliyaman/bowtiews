package com.bowtie.ws.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "u_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "user-sequence-generator")
    @GenericGenerator(
            name = "user-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Getter
    @Setter
    @Column(name = "firstname")
    private String firstname;

    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastname;

    @Getter
    @Setter
    @Column(name = "gender")
    private String gender;

    @Getter
    @Setter
    @Column(name = "birth_date")
    private Date birthDate;

    @Getter
    @Setter
    @Column(name = "mail_address",unique = true)
    private String mailAddress;

    @Getter
    @Setter
    @Column(name = "create_date")
    private Date createDate;

    @Getter
    @Setter
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private UserAccount userAccount;


}
