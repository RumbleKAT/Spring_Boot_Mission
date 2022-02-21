package dev.runblekat.mission2.Entity;

public class Post {
    private Long postId;
    private String title;
    private String content;
    private String writerName;
    private String password;
    private Long boardId; //board 와 연결하는 외래키 id 정보
//TODO : a. 게시글에는 제목, 본문, 작성자, 비밀번호에 대한 정보가 존재합니다.

    public Post(Long postId, String title, String content, String writerName, String password, Long boardId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.password = password;
        this.boardId = boardId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writerName='" + writerName + '\'' +
                ", password='" + password + '\'' +
                ", boardId=" + boardId +
                '}';
    }
}
