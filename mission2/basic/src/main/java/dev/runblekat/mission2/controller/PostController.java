package dev.runblekat.mission2.controller;

import dev.runblekat.mission2.Entity.Post;
import dev.runblekat.mission2.dto.Message;
import dev.runblekat.mission2.dto.PostDto;
import dev.runblekat.mission2.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(@Autowired PostService postService){
        this.postService = postService;
    }

    @PostMapping("create")
    public ResponseEntity<Message> createPost(@RequestBody PostDto postDto){
        logger.info("create post...");
        PostDto postDto1 = postService.create(postDto);
        if(postDto1 != null){
            return new ResponseEntity<>(new Message("S","Post created!",postDto1),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("E","Post created failed!"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("readAll")
    public List<PostDto> readAllBoard(){
        logger.info("read posts all..."  );
        return postService.findAll();
    }

    @GetMapping("read")
    public ResponseEntity<Message> read(@RequestBody PostDto postDto){
        logger.info("read post..."  );
        PostDto postDto1 = postService.findById(postDto.getPostId());
        if(postDto1 == null){
            return new ResponseEntity<>(new Message("E","Not Found"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message("S","Find",postDto1),HttpStatus.OK);
    }

    @PutMapping("modify")
    public ResponseEntity<Message> modify(@RequestBody PostDto postDto){
        logger.info("modify post..."  );
        if(postService.update(postDto.getPostId(),postDto)){
            return new ResponseEntity<>(new Message("S","Post modified!"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("E", "Post modify failed!"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Message> delete(@RequestBody PostDto postDto){
        logger.info("delete post..."  );
        if(postService.delete(postDto.getPostId(), postDto.getPassword())){
            return new ResponseEntity<>(new Message("S","post deleted!"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("E", "post delete failed!"),HttpStatus.BAD_REQUEST);
    }

}
