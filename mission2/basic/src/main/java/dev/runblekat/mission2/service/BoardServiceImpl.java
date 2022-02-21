package dev.runblekat.mission2.service;

import dev.runblekat.mission2.Entity.Board;
import dev.runblekat.mission2.Entity.Post;
import dev.runblekat.mission2.dto.BoardDto;
import dev.runblekat.mission2.dto.PostDto;
import dev.runblekat.mission2.repository.BoardRepository;
import dev.runblekat.mission2.repository.PostRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private final BoardRepository boardRepository;
    private final PostService postService;
    private int boardIdx;


    public BoardServiceImpl(@Autowired BoardRepository boardRepository, @Autowired PostService postService){
        this.boardRepository = boardRepository;
        this.postService = postService;
        this.boardIdx = 0;
    }

    @Override
    public BoardDto create(BoardDto boardDto) {
        logger.info("service layer - create");
        Board board = dtoToEntity(boardDto);
        board.setBoardId((long) boardIdx);
        boardIdx++;

        List<Post> posts = board.getPostList();
        List<Post> postList = new ArrayList<>();

        this.boardRepository.save(board);
        for(Post p : posts){
            p.setBoardId((long)boardIdx);
            PostDto postDto = this.postService.create(entityToDto(p));
            postList.add(dtoToEntity(postDto));
        }

        board.setPostList(postList);

        return entityToDTO(board);
    }

    @Override
    public List<BoardDto> findAll() {
        logger.info("service layer - findAll");
        List<BoardDto> boardList = new ArrayList<>();
        for(Board board : this.boardRepository.findAll()){
            boardList.add(entityToDTO(board));
        }
        return boardList;
    }

    @Override
    public BoardDto findById(Long id) {
        logger.info("service layer - findById");
        Board board = this.boardRepository.findById(id);
        BoardDto boardDto = null;
        if(board != null){
            boardDto = entityToDTO(this.boardRepository.findById(id));
        }
        return boardDto;
    }

    @Override
    public boolean update(Long id, BoardDto boardDto) {
        logger.info("service layer - update");
        return this.boardRepository.update(id,dtoToEntity(boardDto));
    }

    @Override
    public boolean delete(Long id) {
        logger.info("service layer - delete");
        return this.boardRepository.delete(id);
    }
}
