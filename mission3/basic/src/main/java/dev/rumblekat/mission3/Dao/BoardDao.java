package dev.rumblekat.mission3.Dao;

import dev.rumblekat.mission3.dto.BoardDto;
import dev.rumblekat.mission3.entity.BoardEntity;
import dev.rumblekat.mission3.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDao {
    private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
    private final BoardRepository boardRepository;

    public BoardDao(@Autowired BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void createBoard(BoardDto dto) {
        BoardEntity board = new BoardEntity();
        board.setBoard_name(dto.getBoard_name());
        this.boardRepository.save(board);
    }

    public BoardEntity readBoard(int id){
        Optional<BoardEntity> boardEntity = this.boardRepository.findById((long)id);
        if(boardEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return boardEntity.get();
    }

    public Iterator<BoardEntity> readBoardAll(){
        return this.boardRepository.findAll().iterator();
    }

    public void updateBoard(int id, BoardDto dto){
        Optional<BoardEntity> target = this.boardRepository.findById((long)id);
        if(target.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardEntity board = target.get();
        board.setBoard_name(dto.getBoard_name() == null ? board.getBoard_name() : dto.getBoard_name());

        this.boardRepository.save(board);
    }

    public void deleteBoard(int id){
        Optional<BoardEntity> target = this.boardRepository.findById((long)id);
        if(target.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.boardRepository.deleteById((long)id);
    }
}


