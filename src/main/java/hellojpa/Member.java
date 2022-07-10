package hellojpa;

import javax.persistence.*;

// JPA가 로딩될때 JPA가 사용되는 애구나 확인하는 어노테이션
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne // Team과 어떤 관계인지
    @JoinColumn(name = "TEAM_ID") // 어떤 컬럼을 매핑해야하는지
    private Team team;

    public Member(){}

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
