package dev.rumblekat.mission3.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDto {
    private Long id;
    private String board_name;
    private List<PostDto> posts;
}
