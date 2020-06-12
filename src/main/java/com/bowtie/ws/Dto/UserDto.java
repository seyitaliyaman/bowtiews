package com.bowtie.ws.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private Date birthDate;

    @Getter
    @Setter
    private String mailAddress;

    @Getter
    @Setter
    private Date createDate;

    @Getter
    @Setter
    private Boolean isDeleted;

    @Getter
    @Setter
    private UserAccountDto userAccountDto;

}
