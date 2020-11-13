package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@EntityListeners(DuckListener.class)
public class Duck {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @PrePersist
    public void prePersist() {
        // 아이디가 생성 되기 전에 호출 된다.
        System.out.println("Duck.prePersist id=" + id);
    }

    @PostPersist
    public void postPersist() {
        // 아이디가 생성 된 후 호출 된다.
        System.out.println("Duck.postPersist id=" + id);
    }

    @PostLoad
    public void postLoad() {
        System.out.println("Duck.postLoad");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Duck.preRemove");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("Duck.postRemove");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
