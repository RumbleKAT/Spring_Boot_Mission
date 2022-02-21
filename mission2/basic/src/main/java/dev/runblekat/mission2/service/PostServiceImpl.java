package dev.runblekat.mission2.service;

import dev.runblekat.mission2.Entity.Post;
import dev.runblekat.mission2.dto.PostDto;
import dev.runblekat.mission2.repository.PostRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRespository postRespository;
    private int postIdx;

    public PostServiceImpl(@Autowired PostRespository postRespository){
        this.postRespository = postRespository;
        this.postIdx = 0;
    }

    @Override
    public PostDto create(PostDto postDto) {
        logger.info("service layer - create");
        Post post = dtoToEntity(postDto);
        post.setPostId((long)postIdx);
        postIdx++;
        if(this.postRespository.save(post)){
            return entityToDto(post);
        }
        return null;
    }

    @Override
    public List<PostDto> findAll() {
        List<PostDto> postList = new ArrayList<>();
        for(Post post : postRespository.findAll()){
            postList.add(new PostDto(post.getPostId(),post.getTitle(),post.getContent(), post.getWriterName(), post.getPassword(),post.getBoardId()));
        }
        return postList;
    }

    @Override
    public PostDto findById(Long id) {
        Post post = postRespository.findById(id);
        if(post != null)
            return entityToDto(postRespository.findById(id));
        return null;
    }

    @Override
    public boolean update(Long id, PostDto postDto) {
        Post post = dtoToEntity(postDto);
        return postRespository.update(id,post);
    }
    //TODO: c. 게시글을 삭제하기 위해서는 게시글의 비밀번호가 함깨 제공되어야 합니다.
    @Override
    public boolean delete(Long id, String password) {
        Post post = this.postRespository.findById(id);
        if(!password.equals(post.getPassword())){
            throw new RuntimeException("Password is not accruate!");
        }
        return postRespository.delete(id);
    }
}
