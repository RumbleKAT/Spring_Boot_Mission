package dev.runblekat.mission2.Entity;
import java.util.List;

public class Board {

    private Long boardId;
    //TODO : a. 게시판은 게시판 이름에 대한 정보를 가지고 있어야 합니다.
    private String boardName;
    //TODO: b. 작성된 게시글은 게시판에 속해 있어야 합니다.
    private List<Post> postList;

    public Board(Long boardId, String boardName, List<Post> postList){
        this.boardId = boardId;
        this.boardName = boardName;
        this.postList = postList;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", boardName='" + boardName + '\'' +
                ", postList=" + postList +
                '}';
    }
}
