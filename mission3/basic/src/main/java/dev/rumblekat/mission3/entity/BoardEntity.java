package dev.rumblekat.mission3.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "board")
public class BoardEntity extends BaseEntity implements Serializable {
    //TODO : 1.PostEntity 와 BoardEntity 를 만들어 봅시다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String board_name;

    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "boardEntity"
    )//hibernate가 구분하도록 설정
    private List<PostEntity> posts;
}
