package dev.rumblekat.mission3.repository;

import dev.rumblekat.mission3.entity.BoardEntity;
import dev.rumblekat.mission3.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity,Long> {
    List<PostEntity> findAllByWriter(String writer);

}
