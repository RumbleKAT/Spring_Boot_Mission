package dev.rumblekat.mission3.service;

import dev.rumblekat.mission3.Dao.BoardDao;
import dev.rumblekat.mission3.dto.BoardDto;
import dev.rumblekat.mission3.entity.BoardEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final BoardDao boardDao;

    public BoardServiceImpl(@Autowired BoardDao boardDao){
        this.boardDao = boardDao;
    }

    @Override
    public void createBoard(BoardDto boardDto) {
        this.boardDao.createBoard(boardDto);
    }

    @Override
    @Transactional
    public BoardDto readBoard(int id) {
        BoardEntity boardEntity = this.boardDao.readBoard(id);
        System.out.println(boardEntity.getPosts());
        return entityToDto(boardEntity);
    }

    @Override
    @Transactional
    public List<BoardDto> readBoardAll() {
        Iterator<BoardEntity> iterator = this.boardDao.readBoardAll();
        List<BoardDto> boardList = new ArrayList<>();

        while(iterator.hasNext()){
            BoardEntity board = iterator.next();
            boardList.add(entityToDto(board));
        }
        return boardList;
    }

    @Override
    @Transactional
    public void updateBoard(int id, BoardDto boardDto) {
        this.boardDao.updateBoard(id,boardDto);
    }

    @Override
    public void deleteBoard(int id) {
        this.boardDao.deleteBoard(id);
    }
}
