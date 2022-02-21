package dev.runblekat.mission2.dto;

//TODO: Abstract class를 붙이면, 자동으로 json으로 변경되는데 이부분 제거 하려면 어떻게 할까요?

public abstract class DtoObj {
    private Long id;

    public DtoObj(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
