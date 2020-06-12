package com.bowtie.ws.Service;

import com.bowtie.ws.Dto.UserAccountDto;
import com.bowtie.ws.Entity.UserAccount;
import com.bowtie.ws.Exceptions.UserAccountNotFoundException;
import com.bowtie.ws.Repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;


    public UserAccount getAccountByUsernameAndPassword(UserAccountDto user){
        Optional<UserAccount> account = userAccountRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());

        if(account.isPresent()){
            return account.get();
        }else{
            throw new UserAccountNotFoundException();
        }
    }


    public UserAccount fromDto(UserAccountDto userAccountDto){

        UserAccount userAccount = new UserAccount();
        userAccount.setPassword(userAccountDto.getPassword());
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setIsDeleted(false);

        return userAccount;
    }

}
