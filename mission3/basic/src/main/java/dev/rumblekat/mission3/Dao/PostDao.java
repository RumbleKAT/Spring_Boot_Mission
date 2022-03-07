package dev.rumblekat.mission3.Dao;

import dev.rumblekat.mission3.dto.PostDto;
import dev.rumblekat.mission3.entity.BoardEntity;
import dev.rumblekat.mission3.entity.PostEntity;
import dev.rumblekat.mission3.entity.UserEntity;
import dev.rumblekat.mission3.repository.PostRepository;
import dev.rumblekat.mission3.repository.UserRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDao(@Autowired PostRepository postRepository,
                   @Autowired UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createPost(PostDto dto){
        PostEntity post = new PostEntity();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        Optional<UserEntity> user = userRepository.findByName(dto.getWriter());
        UserEntity user1 = UserEntity.builder().name(dto.getWriter()).build();
        if(user.isEmpty()){
            //user가 없는 경우, user를 생성한다.
            userRepository.save(user1);
        }
        post.setWriter(user1);
        //TODO: 2.Post 를 작성하는 단계에서, User 의 정보를 어떻게 전달할지 고민해 봅시다.
//        post.setBoardEntity(BoardEntity.builder().id((long)dto.getBoardId()).build());
        post.setBoardEntity(null);
        this.postRepository.save(post);
    }

    public PostEntity readPost(int id){
        Optional<PostEntity> postEntity = this.postRepository.findById((long)id);
        if(postEntity.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return postEntity.get();
    }

    public Iterator<PostEntity> readPostAll(){
        return this.postRepository.findAll().iterator();
    }

    @Transactional
    public void updatePost(int id, PostDto dto){
        Optional<PostEntity> target = this.postRepository.findById((long)id);
        if(target.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PostEntity post = target.get();
        post.setTitle(dto.getTitle() == null ? post.getTitle() : dto.getTitle());
        post.setContent(dto.getContent() == null ? post.getContent() : dto.getContent());
        //TODO: 2.Post 를 작성하는 단계에서, User 의 정보를 어떻게 전달할지 고민해 봅시다.
        Optional<UserEntity> user = userRepository.findByName(dto.getWriter());
        if(user.isEmpty()){
            //user가 없는 경우, 에러를 리턴한다.
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        post.setWriter(dto.getWriter() == null ? post.getWriter() : user.get());

        this.postRepository.save(post);
    }

    public void deletePost(int id){
       Optional<PostEntity> target = this.postRepository.findById((long)id);
       if(target.isEmpty()){
           throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       this.postRepository.deleteById((long)id);
    }
}
