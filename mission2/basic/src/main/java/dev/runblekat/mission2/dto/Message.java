package dev.runblekat.mission2.dto;

public class Message {
    private String type;
    private String content;
    private DtoObj resultContent;

    public Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public Message(String type, String content, DtoObj resultContent) {
        this.type = type;
        this.content = content;
        this.resultContent = resultContent;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public DtoObj getResultContent() {
        return resultContent;
    }

    public void setResultContent(DtoObj resultContent) {
        this.resultContent = resultContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", resultContent=" + resultContent +
                '}';
    }
}
