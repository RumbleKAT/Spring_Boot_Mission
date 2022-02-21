package dev.runblekat.mission2.repository;

import dev.runblekat.mission2.Entity.Board;
import dev.runblekat.mission2.Entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostInMemoryRepository implements PostRespository {

    private static final Logger logger = LoggerFactory.getLogger(PostInMemoryRepository.class);
    private final List<Post> postList;

    PostInMemoryRepository(){
        this.postList = new ArrayList<>();
    }

    @Override
    public boolean save(Post post) {
        logger.info("repository layer - post save");
        if(this.findById(post.getPostId()) == null){
            return this.postList.add(post);
        }
        return false;
    }

    @Override
    public List<Post> findAll() {
        logger.info("repository layer - post findAll ");
        return this.postList;
    }

    @Override
    public Post findById(Long id) {
        logger.info("repository layer - post findById ");

        Post post = null;
        for(Post p : this.postList){
            if(p.getPostId().equals(id)){
                post = p;
                break;
            }
        }
        return post;
    }

    @Override
    public boolean update(Long id, Post post) {
        logger.info("repository layer - post update ");
        Post postPrev = this.findById(id);
        if(postPrev == null) return false;

        this.postList.remove(postPrev);
        postPrev.setTitle(post.getTitle());
        postPrev.setContent(post.getContent());

        this.postList.add(postPrev);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        logger.info("repository layer - post delete ");
        Post postPrev = this.findById(id);
        return this.postList.remove(postPrev);
    }

    @Override
    public List<Post> findByBoardId(Long id) {
        List<Post> postList = new ArrayList<>();
        for(Post p : this.postList){
            if(p.getBoardId() == id){
                postList.add(p);
            }
        }
        return postList;
    }
}
