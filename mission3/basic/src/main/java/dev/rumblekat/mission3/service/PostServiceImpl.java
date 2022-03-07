package dev.rumblekat.mission3.service;

import dev.rumblekat.mission3.Dao.PostDao;
import dev.rumblekat.mission3.dto.PostDto;
import dev.rumblekat.mission3.entity.PostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostDao postDao;

    public PostServiceImpl(@Autowired PostDao postDao){
        this.postDao = postDao;
    }

    public void createPost(PostDto postDto){
        this.postDao.createPost(postDto);
    }

    public PostDto readPost(int id){
        PostEntity postEntity = this.postDao.readPost(id);
        return entityToDto(postEntity);
    }

    public List<PostDto> readPostAll(){
        Iterator<PostEntity> iterator = this.postDao.readPostAll();
        List<PostDto> postList = new ArrayList<>();

        while(iterator.hasNext()){
            PostEntity post = iterator.next();
            postList.add(entityToDto(post));
        }
        return postList;
    }

    public void updatePost(int id, PostDto postDto){
        this.postDao.updatePost(id,postDto);
    }

    public void deletePost(int id){
        this.postDao.deletePost(id);
    }
}

