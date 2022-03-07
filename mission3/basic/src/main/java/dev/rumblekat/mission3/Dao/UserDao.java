package dev.rumblekat.mission3.Dao;

import dev.rumblekat.mission3.dto.UserDto;
import dev.rumblekat.mission3.entity.UserEntity;
import dev.rumblekat.mission3.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final UserRepository userRepository;

    public UserDao(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //TODO: 세부 - 1. UserEntity 에 대한 CRUD를 작성합시다.
    public void createUser(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setName(dto.getUser_name());
        this.userRepository.save(user);
    }

    public UserEntity readUser(int id) {
        Optional<UserEntity> userEntity = this.userRepository.findById((long)id);
        if(userEntity.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userEntity.get();
    }

    public Iterator<UserEntity> readUserAll() {
        return this.userRepository.findAll().iterator();
    }

    public void updateUser(int id, UserDto dto){
        Optional<UserEntity> target = this.userRepository.findById((long)id);
        if(target.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserEntity user = target.get();
        user.setName(dto.getUser_name() == null ? user.getName() : dto.getUser_name());

        this.userRepository.save(user);
    }

    public void deleteUser(int id){
        Optional<UserEntity> target = this.userRepository.findById((long)id);
        if(target.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.userRepository.deleteById((long)id);
    }
}
