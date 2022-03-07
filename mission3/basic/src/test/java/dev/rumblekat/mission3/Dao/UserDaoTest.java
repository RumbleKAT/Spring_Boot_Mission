package dev.rumblekat.mission3.Dao;

import dev.rumblekat.mission3.dto.PostDto;
import dev.rumblekat.mission3.dto.UserDto;
import dev.rumblekat.mission3.entity.PostEntity;
import dev.rumblekat.mission3.entity.UserEntity;
import dev.rumblekat.mission3.repository.UserRepository;
import dev.rumblekat.mission3.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    PostDao postDao;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findUserByName(){
        userDao.createUser(UserDto.builder().user_name("song").build());
        Optional<UserEntity> user = userRepository.findByName("song");
        System.out.println(user.get().getName());
        assertThat(user.get().getName().equals("song"),is(true));
    }

    @Test
    public void readPost(){
        PostDto postDto = PostDto.builder()
                .title("sample...")
                .content("sample..content")
                .writer("song")
                .boardId(1)
                .build();

        postDao.createPost(postDto);

        PostEntity entity = postDao.readPost(1);
        System.out.println(entity.getWriter().getName());
//        System.out.println(postDao.readPost(1));
    }

    @Test
    @Transactional
    public void createPostandUpdate(){
        PostDto postDto = PostDto.builder()
                .title("sample...")
                .content("sample..content")
                .writer("song")
                .boardId(1)
                .build();

        postDao.createPost(postDto);
        assertThat(userRepository.findByName("song").get().getName(),is("song"));

        PostEntity post = postDao.readPost(1);
        PostDto postDto1 = PostDto.builder()
                .id(Math.toIntExact(post.getId()))
                .title(post.getTitle())
                .content("content_change.....")
                .writer("song")
                .boardId(1)
                .build();

        postDao.updatePost(1,postDto1);
        post = postDao.readPost(1);
        assertThat(post.getContent(),is("content_change....."));
    }


}