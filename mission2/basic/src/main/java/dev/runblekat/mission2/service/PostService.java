package dev.runblekat.mission2.service;

import dev.runblekat.mission2.Entity.Post;
import dev.runblekat.mission2.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto create(PostDto postDto);
    List<PostDto> findAll();
    PostDto findById(Long id);
    boolean update(Long id, PostDto postDto);
    boolean delete(Long id, String password);

    default Post dtoToEntity(PostDto postDto){
        Post post = new Post(postDto.getPostId(),postDto.getTitle(), postDto.getContent(), postDto.getWriterName(), postDto.getPassword(), postDto.getBoardId());
        return post;
    }

    default PostDto entityToDto(Post post){
        PostDto postDto = new PostDto(post.getPostId(), post.getTitle(), post.getContent(), post.getWriterName(), post.getPassword(), post.getBoardId());
        return postDto;
    }
}
