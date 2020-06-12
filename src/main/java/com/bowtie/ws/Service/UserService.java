package com.bowtie.ws.Service;

import com.bowtie.ws.Dto.UserDto;
import com.bowtie.ws.Entity.User;
import com.bowtie.ws.Entity.UserAccount;
import com.bowtie.ws.Exceptions.UserNotFoundException;
import com.bowtie.ws.Model.FacebookUserModel;
import com.bowtie.ws.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAccountService userAccountService;

    public User  registerUser(UserDto userDto){
        User user = fromDto(userDto);
        userRepository.save(user);
        return user;
    }

    public User getUserByAccount(UserAccount userAccount){

        Optional<User> user = userRepository.findByUserAccount(userAccount);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException();
        }
    }

    public User checkFacebookUserByMail(FacebookUserModel facebookUserModel) throws ParseException {
        Optional<User> user = userRepository.findByMailAddress(facebookUserModel.getEmail());
        if(user.isPresent()){
            return user.get();
        }else{

            User newUser = new User();
            newUser.setIsDeleted(false);
            newUser.setCreateDate(new Date());
            newUser.setMailAddress(facebookUserModel.getEmail());
            newUser.setBirthDate(new SimpleDateFormat("MM/dd/yyyy").parse(facebookUserModel.getBirthday()));
            newUser.setGender(facebookUserModel.getGender());
            newUser.setFirstname(facebookUserModel.getFirst_name());
            newUser.setLastname(facebookUserModel.getLast_name());
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername(facebookUserModel.getEmail());
            userAccount.setPassword(facebookUserModel.getId().toString());
            userAccount.setIsDeleted(false);
            newUser.setUserAccount(userAccount);
            userRepository.save(newUser);
            return newUser;
        }
    }


    public User fromDto(UserDto userDto){
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setGender(userDto.getGender());
        user.setBirthDate(userDto.getBirthDate());
        user.setMailAddress(userDto.getMailAddress());
        user.setCreateDate(new Date());
        user.setIsDeleted(false);
        user.setUserAccount(userAccountService.fromDto(userDto.getUserAccountDto()));
        return user;
    }

}
