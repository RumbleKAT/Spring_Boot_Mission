package dev.rumblekat.mission3.controller;

import dev.rumblekat.mission3.dto.PostDto;
import dev.rumblekat.mission3.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(@Autowired PostService postService){
        this.postService = postService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto dto , @PathVariable("boardId") int boardId){
        this.postService.createPost(dto);
    }

    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") int id, @PathVariable("boardId") int boardId){
        return this.postService.readPost(id);
    }

    @GetMapping("")
    public List<PostDto> readPostAll(@PathVariable("boardId") int boardId){
        return this.postService.readPostAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(@PathVariable("id") int id, @RequestBody PostDto postDto, @PathVariable("boardId") int boardId){
        this.postService.updatePost(id,postDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable("id") int id, @PathVariable("boardId") int boardId){
        this.postService.deletePost(id);
    }

}
