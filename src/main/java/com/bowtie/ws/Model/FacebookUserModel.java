package com.bowtie.ws.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class FacebookUserModel {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String first_name;
    @Getter
    @Setter
    private String last_name;
    @Getter
    @Setter
    private String birthday;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private String email;
}
