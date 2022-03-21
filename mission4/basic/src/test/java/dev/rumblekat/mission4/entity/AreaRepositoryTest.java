package dev.rumblekat.mission4.entity;

import dev.rumblekat.mission4.utils.ResidenceUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AreaRepositoryTest {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UserRepository userRepository;
    /*
      1. 서울시 서초구 서초동, 37.4877° N, 127.0174° E
      2. 서울시 강남구 역삼동, 37.4999° N, 127.0374° E
      3. 서울시 강남구 삼성동, 37.5140° N, 127.0565° E
    * */
    @Autowired
    ResidenceUtil residenceUtil;

    @BeforeEach
    public void setUpArea(){
        List<AreaEntity> areaEntityList = new ArrayList<>();
        areaEntityList.add(new AreaEntity((long) 1, "서울시 서초구 서초동", "37.4877° N", "127.0174° E"));
        areaEntityList.add(new AreaEntity((long) 2, "서울시 강남구 역삼동", "37.4999° N", "127.0374° E"));
        areaEntityList.add(new AreaEntity((long) 3, "서울시 강남구 삼성동", "37.5140° N", "127.0565° E"));

        for (var entity : areaEntityList) {
            areaRepository.save(entity);
        }
    }

    @Test
    public void getArea() {
        List<AreaEntity> list = (List<AreaEntity>) areaRepository.findAll();
        System.out.println(list.size());
    }

    @Test
    @Transactional
    public void insertDummyUser() {
        Optional<AreaEntity> areaEntity = areaRepository.findById((long)1);
        if(areaEntity.isPresent()){
            List<AreaEntity> alist = new ArrayList<>();
            alist.add(areaEntity.get());
            UserEntity userEntity = new UserEntity((long)1,"song","1234","reki","admin",true);
            userEntity.setResidence(alist);

            userRepository.save(userEntity);
            Optional<UserEntity> user = userRepository.findById((long)1);
            System.out.println(user.get().getResidence().get(0));
        }
    }

    @Test
    public void getResidenceRandom(){
        System.out.println(residenceUtil.getResidence().get());
    }

}