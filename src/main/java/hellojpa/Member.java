package hellojpa;

import javax.persistence.*;
import java.util.Date;

// JPA가 로딩될때 JPA가 사용되는 애구나 확인하는 어노테이션
@Entity
// class명과 table명이 다를 경우 명시해주면 JPA가 적힌 USER라는 테이블로 처리해줌
//@Table(name = "USER")
public class Member {

    @Id // @Id 는 PK가 무엇인지 알려주기 위해 명시
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member(){}



}
