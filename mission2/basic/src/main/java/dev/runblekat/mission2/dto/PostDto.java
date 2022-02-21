package dev.runblekat.mission2.dto;

public class PostDto extends DtoObj{
    private String title;
    private String content;
    private String writerName;
    private String password;
    private Long boardId;

    public PostDto(Long postId, String title, String content, String writerName, String password,Long boardId){
        super(postId);
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.password = password;
        this.boardId = boardId;
    }

    public Long getPostId() {
        return super.getId();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getPassword() {
        return password;
    }

    public Long getBoardId() {
        return boardId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writerName='" + writerName + '\'' +
                ", password='" + password + '\'' +
                ", boardId=" + boardId +
                '}';
    }
}

/*
* TODO: 2. 게시글에 대한 CRUD를 작성해 봅시다.
    1. 게시글에는 제목, 본문, 작성자, 비밀번호에 대한 정보가 존재합니다.
    2. 작성된 게시글은 게시판에 속해 있어야 합니다.
    3. 게시글을 삭제하기 위해서는 게시글의 비밀번호가 함깨 제공되어야 합니다.
*
* */
