package com.bowtie.ws.Service;

import com.bowtie.ws.Entity.User;
import com.bowtie.ws.Model.FacebookUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class FacebookService {
    @Autowired
    private UserService userService;

    private FacebookUserModel getFacebookProfileInfo(final String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        FacebookUserModel facebook = null;
        final String fields = "id,first_name,last_name,birthday,gender,email";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("https://graph.facebook.com/me")
                    .queryParam("access_token", accessToken).queryParam("fields", fields);

            facebook = restTemplate.getForObject(uriBuilder.toUriString(), FacebookUserModel.class);

        } catch (HttpClientErrorException e) {
        } catch (Exception exp) {
        }
        return facebook;
    }


    public User checkFacebookUser(String accessToken) throws ParseException {
        FacebookUserModel facebookUserModel = getFacebookProfileInfo(accessToken);
        User user = userService.checkFacebookUserByMail(facebookUserModel);
        return user;
    }
}
