package com.bowtie.ws.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "u_user_account")
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "useraccount-sequence-generator")
    @GenericGenerator(
            name = "useraccount-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "useraccount_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Getter
    @Setter
    @Column(name = "username",unique = true)
    private String username;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
