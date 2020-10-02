package jpabook.jpashop.hello_jpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
