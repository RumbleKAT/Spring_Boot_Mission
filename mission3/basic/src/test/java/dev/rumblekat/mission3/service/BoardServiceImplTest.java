package dev.rumblekat.mission3.service;

import dev.rumblekat.mission3.entity.BoardEntity;
import dev.rumblekat.mission3.entity.PostEntity;
import dev.rumblekat.mission3.entity.UserEntity;
import dev.rumblekat.mission3.repository.BoardRepository;
import dev.rumblekat.mission3.repository.PostRepository;
import dev.rumblekat.mission3.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class BoardServiceImplTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        int idx = 1;
        for(int i=1;i<=2;i++) {
            BoardEntity board = BoardEntity.builder()
                    .id((long)i)
                    .board_name("board" + i)
                    .build();

            BoardEntity newBoard = boardRepository.save(board);
            for (int j = 1; j <= 2; j++) {
                UserEntity user = userRepository.save(UserEntity.builder().id((long)idx).name("sample..." + i+j).build());
                PostEntity post = PostEntity.builder()
                        .title("sample" + j)
                        .content("sample content" + j)
                        .writer(user)
                        .boardEntity(newBoard)
                        .build();
                postRepository.save(post);
                idx++;
            }
        }
    }
    //TODO: JUnit 테스트 시, 테스트 데이터 초기화 문제?

    @Test
    @Description("부분 조회")
    @Transactional
    public void SelectById(){
        List<Object[]> lists = boardRepository.getPostsWithBoardId((long)1);
        System.out.println(lists.size());
        for(var list : lists){
            System.out.println(Arrays.toString(list));
        }
        assertThat(lists.size(), is(equalTo(2)));
    }

    @Test
    @Description("전체 조회")
    @Transactional
    public void SelectAll(){
        List<Object[]>lists = boardRepository.getPostsWithBoard();
        System.out.println(lists.size());
        for(var list : lists){
            System.out.println(Arrays.toString(list));
        }
        assertThat(lists.size(), is(equalTo(4)));
    }

}