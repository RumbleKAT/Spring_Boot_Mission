package dev.runblekat.mission2.service;

import dev.runblekat.mission2.Entity.Board;
import dev.runblekat.mission2.Entity.Post;
import dev.runblekat.mission2.dto.BoardDto;
import dev.runblekat.mission2.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {
    BoardDto create(BoardDto board);
    List<BoardDto> findAll();
    BoardDto findById(Long id);
    boolean update(Long id, BoardDto boardDto);
    boolean delete(Long id);

    default Board dtoToEntity(BoardDto boardDto){
        List<Post> postList = new ArrayList<>();
        for(PostDto post : boardDto.getPostList()){
            postList.add(new Post(post.getPostId(),post.getTitle(), post.getContent(), post.getWriterName(),post.getPassword(),post.getBoardId()));
        }
        Board board = new Board(boardDto.getBoardId(),boardDto.getBoardName(), postList);
        return board;
    }

    default BoardDto entityToDTO(Board board){
        List<PostDto> postList = new ArrayList<>();
        for(Post post : board.getPostList()){
            postList.add(new PostDto(post.getPostId(),post.getTitle(), post.getContent(), post.getWriterName(),post.getPassword(),post.getBoardId()));
        }
        BoardDto boardDto = new BoardDto(board.getBoardId(),board.getBoardName(),postList);
        return boardDto;
    }

    default Post dtoToEntity(PostDto postDto){
        Post post = new Post(postDto.getPostId(),postDto.getTitle(), postDto.getContent(), postDto.getWriterName(), postDto.getPassword(),postDto.getBoardId());
        return post;
    }

    default PostDto entityToDto(Post post){
        PostDto postDto = new PostDto(post.getPostId(), post.getTitle(), post.getContent(), post.getWriterName(), post.getPassword(),post.getBoardId());
        return postDto;
    }
}
