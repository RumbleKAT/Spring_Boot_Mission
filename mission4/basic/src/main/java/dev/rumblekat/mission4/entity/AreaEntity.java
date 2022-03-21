package dev.rumblekat.mission4.entity;

import javax.persistence.*;

@Entity
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adress;

    private String north;

    private String east;

    public AreaEntity(Long id, String adress, String north, String east) {
        this.id = id;
        this.adress = adress;
        this.north = north;
        this.east = east;
    }

    @ManyToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public AreaEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "AreaEntity{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", north='" + north + '\'' +
                ", east='" + east + '\'' +
                '}';
    }
}
