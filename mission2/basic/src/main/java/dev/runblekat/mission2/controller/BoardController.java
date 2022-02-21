package dev.runblekat.mission2.controller;

import dev.runblekat.mission2.dto.BoardDto;
import dev.runblekat.mission2.dto.Message;
import dev.runblekat.mission2.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static BoardService boardService;

    public BoardController(@Autowired BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping("create")
    public ResponseEntity<Message> createBoard(@RequestBody BoardDto boardDto){
        logger.info("create board..."  );
        BoardDto boardDto2 = this.boardService.create(boardDto);
        if(boardDto2 != null){
            return new ResponseEntity<>(new Message("S","Board modified!", boardDto2),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("E", "Board modify failed!"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("readAll")
    public List<BoardDto> readAllBoard(){
        logger.info("read board all..."  );
        return boardService.findAll();
    }

    @GetMapping("read")
    public ResponseEntity<Message> read(@RequestBody BoardDto boardDto){
        logger.info("read board..."  );
        BoardDto boardDto1 =  boardService.findById(boardDto.getBoardId());
        if(boardDto1 == null){
            return new ResponseEntity<>(new Message("E","Not Found"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message("S","Find",boardDto1),HttpStatus.OK);
    }

    @PutMapping("modify")
    public ResponseEntity<Message> modify(@RequestBody BoardDto boardDto){
        logger.info("modify board..."  );
        if(boardService.update(boardDto.getBoardId(),boardDto)){
            return new ResponseEntity<>(new Message("S","Board modified!"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("E", "Board modify failed!"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Message> delete(@RequestBody BoardDto boardDto){
        logger.info("delete board..."  );
        if(boardService.delete(boardDto.getBoardId())){
            return new ResponseEntity<>(new Message("S","board deleted!"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("E", "board delete failed!"),HttpStatus.BAD_REQUEST);
    }
}
