package dev.rumblekat.mission3.service;

import dev.rumblekat.mission3.dto.PostDto;
import dev.rumblekat.mission3.entity.PostEntity;
import dev.rumblekat.mission3.entity.UserEntity;

import java.util.List;

public interface PostService {

    default PostEntity dtoToEntity(PostDto postDto){
        PostEntity post = PostEntity.builder()
                .id((long)postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .writer(UserEntity.builder().name(postDto.getWriter()).build()).build();

        //TODO: Board Entity Created
        return post;
    }

    default PostDto entityToDto(PostEntity post){
        PostDto postDto = PostDto.builder()
                .id(Math.toIntExact(post.getId()))
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter().getName())
                .boardId(post.getBoardEntity() == null ? 0 : Math.toIntExact(post.getBoardEntity().getId())).build();
        return postDto;
    }

    void createPost(PostDto postDto);
    PostDto readPost(int id);
    List<PostDto> readPostAll();
    void updatePost(int id, PostDto postDto);
    void deletePost(int id);
}
