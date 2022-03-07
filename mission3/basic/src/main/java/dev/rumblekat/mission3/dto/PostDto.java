package dev.rumblekat.mission3.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int boardId;
}
