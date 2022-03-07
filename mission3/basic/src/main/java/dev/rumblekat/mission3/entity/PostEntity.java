package dev.rumblekat.mission3.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"boardEntity", "writer"})
@Builder
@Table(name = "post")
public class PostEntity extends BaseEntity implements Serializable {
    //TODO : 1.PostEntity 와 BoardEntity 를 만들어 봅시다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "name" , referencedColumnName="name")
    private UserEntity writer;

    //TODO : 2.@ManyToOne , @OneToMany, @JoinColumn 을 적절히 사용합시다.
    @ManyToOne(
            targetEntity = BoardEntity.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private BoardEntity boardEntity;
    //TODO: boardEntity에서 id만 넣는 방법?

}
