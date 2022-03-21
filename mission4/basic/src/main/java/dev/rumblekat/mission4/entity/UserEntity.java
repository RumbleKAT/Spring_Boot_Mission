package dev.rumblekat.mission4.entity;

import javax.persistence.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO:  1. `username` , `password` 는 일반적인 서비스의 아이디, 비밀번호로 활용됩니다.
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String role;

    //TODO: 3. `isShopOwner` 는 회원가입 시에 추가되어야 합니다.
    @Column
    private boolean isShopOwner;


    //TODO: 2. `residence` 는 `AreaEntity` 를 필요로 합니다.
    @OneToMany(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY)
    private List<AreaEntity> residence = new ArrayList<>();

    public UserEntity(){
    }

    public UserEntity(Long id, String username, String password, String nickname, String role, boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.isShopOwner = isShopOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isShopOwner() {
        return isShopOwner;
    }

    public void setShopOwner(boolean shopOwner) {
        isShopOwner = shopOwner;
    }

    public List<AreaEntity> getResidence() {
        return residence;
    }

    public void setResidence(List<AreaEntity> residence) {
        this.residence = residence;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role='" + role + '\'' +
                ", isShopOwner=" + isShopOwner +
                '}';
    }
}
