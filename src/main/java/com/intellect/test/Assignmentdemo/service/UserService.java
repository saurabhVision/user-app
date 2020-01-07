package com.intellect.test.Assignmentdemo.service;

import antlr.StringUtils;
import com.intellect.test.Assignmentdemo.Util.DateConverterUtil;
import com.intellect.test.Assignmentdemo.exception.RecordNotFoundException;
import com.intellect.test.Assignmentdemo.model.UserEntity;
import com.intellect.test.Assignmentdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> userList = repository.findAll();

        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }

    public UserEntity createOrUpdateUser(UserEntity entity) throws RecordNotFoundException
    {
        Optional<UserEntity> userEntity = repository.findByEmail(entity.getEmail());
        Optional<UserEntity> userEntityId = repository.findById(entity.getId());
        DateConverterUtil util = new DateConverterUtil();
        String date = null;
        try {
            date = util.convertDateFormat(entity.getBirthdate());
        } catch (Exception e) {
            throw new RecordNotFoundException("Enter valid date format");
        }
        if(userEntity.isPresent())
        {
            throw new RecordNotFoundException("Email Id already exist");
        }
        else if(userEntityId.isPresent()){
            UserEntity newEntity = userEntityId.get();
            if(date != null && date != ""){
                newEntity.setBirthdate(date);
            }
            newEntity.setPincode(entity.getPincode());
            newEntity = repository.save(newEntity);

            return newEntity;
        }
        else {
            entity.setEmail(entity.getEmail());
            entity.setFirstName(entity.getFirstName());
            entity.setLastName(entity.getLastName());
            entity.setPincode(entity.getPincode());
            if(date != null && date != ""){
                entity.setBirthdate(date);
            }
            entity = repository.save(entity);

            return entity;
        }
    }

    public List<UserEntity> getCurrentBirthDate()
    {
        List<UserEntity> userBirthdateList = repository.findBirthdayCurrentMonth();

        if(userBirthdateList.size() > 0) {
            return userBirthdateList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }

    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

}
