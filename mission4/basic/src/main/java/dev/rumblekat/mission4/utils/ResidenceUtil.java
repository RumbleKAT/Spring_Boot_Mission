package dev.rumblekat.mission4.utils;

import dev.rumblekat.mission4.entity.AreaEntity;
import dev.rumblekat.mission4.entity.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class ResidenceUtil {

    private final AreaRepository areaRepository;

    ResidenceUtil(@Autowired AreaRepository areaRepository){
        this.areaRepository = areaRepository;

        List<AreaEntity> areaEntityList = new ArrayList<>();
        areaEntityList.add(new AreaEntity((long) 1, "서울시 서초구 서초동", "37.4877° N", "127.0174° E"));
        areaEntityList.add(new AreaEntity((long) 2, "서울시 강남구 역삼동", "37.4999° N", "127.0374° E"));
        areaEntityList.add(new AreaEntity((long) 3, "서울시 강남구 삼성동", "37.5140° N", "127.0565° E"));

        for (var entity : areaEntityList) {
            this.areaRepository.save(entity);
        }
    }

    //TODO: AreaEntity 의 경우, 더미 데이터를 우선 활용합니다.
    public Optional<AreaEntity> getResidence(){
        return this.areaRepository.findById((long)(Math.random()*3+1));
    }
}
