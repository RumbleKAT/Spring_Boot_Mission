package dev.runblekat.mission2.dto;

import java.util.List;

public class BoardDto extends DtoObj{
    private String boardName;
    private List<PostDto> postList;

    public BoardDto(Long boardId, String boardName, List<PostDto> postList){
        super(boardId);
        this.boardName = boardName;
        this.postList = postList;
    }

    public Long getBoardId() {
        return super.getId();
    }

    public String getBoardName() {
        return boardName;
    }

    public List<PostDto> getPostList() {
        return postList;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                ", boardName='" + boardName + '\'' +
                ", postList=" + postList +
                '}';
    }
}
/*
* TODO: 1. 게시판에 대한 CRUD를 우선 작성합니다.
    1. 게시판은 게시판 이름에 대한 정보를 가지고 있어야 합니다.
*
* */