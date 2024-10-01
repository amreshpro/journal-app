package com.amreshpro.journal.service;

import com.amreshpro.journal.entity.UserEntity;
import com.amreshpro.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Boolean saveUser(UserEntity userEntity) {
        userEntity.setDate(LocalDateTime.now());
        userRepository.save(userEntity);
        return true;
    }

    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public Boolean updateUserById(UserEntity userEntity) {
        Boolean isUserExist = userRepository.existsById(userEntity.getUserId());
        if (isUserExist) {
            userEntity.setDate(LocalDateTime.now());
            userRepository.save(userEntity);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteUserById(ObjectId id) {
        Boolean isUserExist = userRepository.existsById(id);
        if (isUserExist) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
