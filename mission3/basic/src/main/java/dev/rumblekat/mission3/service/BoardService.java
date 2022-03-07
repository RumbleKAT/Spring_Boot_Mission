package dev.rumblekat.mission3.service;

import dev.rumblekat.mission3.dto.BoardDto;
import dev.rumblekat.mission3.dto.PostDto;
import dev.rumblekat.mission3.entity.BoardEntity;
import dev.rumblekat.mission3.entity.PostEntity;

import java.util.List;

public interface BoardService {

    void createBoard(BoardDto boardDto);

    BoardDto readBoard(int id);

    List<BoardDto> readBoardAll();

    void updateBoard(int id, BoardDto boardDto);

    void deleteBoard(int id);

    default BoardDto entityToDto(BoardEntity board) {
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .board_name(board.getBoard_name())
                .build();
        return boardDto;
    }

    default BoardEntity dtoToEntity(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(boardDto.getId())
                .board_name(boardDto.getBoard_name())
                .build();
        return boardEntity;
    }
}
