package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// JPA가 로딩될때 JPA가 사용되는 애구나 확인하는 어노테이션
@Entity
// class명과 table명이 다를 경우 명시해주면 JPA가 적힌 USER라는 테이블로 처리해줌
//@Table(name = "USER")
public class Member {

    @Id // @Id 는 PK가 무엇인지 알려주기 위해 명시
    private Long id;
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
