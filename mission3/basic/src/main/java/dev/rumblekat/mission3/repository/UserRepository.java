package dev.rumblekat.mission3.repository;

import dev.rumblekat.mission3.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findByName(String name);
}
