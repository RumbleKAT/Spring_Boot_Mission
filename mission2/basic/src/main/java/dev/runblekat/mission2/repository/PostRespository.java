package dev.runblekat.mission2.repository;

import dev.runblekat.mission2.Entity.Post;
import dev.runblekat.mission2.dto.PostDto;

import java.util.List;

public interface PostRespository {
    boolean save(Post post);
    List<Post> findAll();
    Post findById(Long id);
    boolean update(Long id, Post post);
    boolean delete(Long id);
    List<Post> findByBoardId(Long id);
}
