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
public class BoardInMemoryRepository implements BoardRepository {

    private static final Logger logger = LoggerFactory.getLogger(BoardInMemoryRepository.class);
    private final List<Board> boardList;
    private final PostRespository postRepository;

    public BoardInMemoryRepository(@Autowired PostRespository postRespository){
        this.boardList = new ArrayList<>();
        this.postRepository = postRespository;
    }

    @Override
    public boolean save(Board board) {
        logger.info("repository layer - board save ");
        if(this.findById(board.getBoardId()) == null){
            return this.boardList.add(board);
        }
        return false;
    }

    @Override
    public List<Board> findAll() {
        logger.info("repository layer - board findAll ");
        return this.boardList;
    }

    @Override
    public Board findById(Long id) {
        logger.info("repository layer - board findById ");
        Board board = null;
        for(Board b : this.boardList){
            if(b.getBoardId().equals(id)){
                board = b;
                break;
            }
        }
        return board;
    }

    @Override
    public boolean update(Long id, Board board) {
        logger.info("repository layer - board update ");
        Board boardPrev = this.findById(id);
        this.boardList.remove(boardPrev);
        boardPrev.setBoardName(board.getBoardName());

        boardPrev.setPostList(board.getPostList());
        this.boardList.add(boardPrev);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        logger.info("repository layer - board delete ");

        Board boardPrev = this.findById(id);
        this.boardList.remove(boardPrev);

        List<Post> postList = this.postRepository.findByBoardId(boardPrev.getBoardId());
        for(Post p : postList){
            this.postRepository.delete(p.getPostId());
        }
        return true;
    }
}
