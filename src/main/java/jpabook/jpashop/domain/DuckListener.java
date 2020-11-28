package jpabook.jpashop.domain;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

// 리스너는 대상 엔티티를 파라미터로 받을 수 있다. 반환 타입은 void 로 해야 한다.
public class DuckListener {

    @PrePersist
    // 특정 타입이 확실하면 특정 타입을 받을 수 있다.
    private void prePersist(Duck duck) {
        System.out.println("DuckListener.prePersist obj = [" + duck.getName() + "]");
    }

    @PostPersist
    // 특정 타입이 확실하면 특정 타입을 받을 수 있다.
    private void postPersist(Duck duck) {
        System.out.println("DuckListener.postPersist obj = [" + duck.getName() + "]");
    }

}
