package hello.hellospring.domain;


import javax.persistence.*;

@Entity // jpa가 관리하는 entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 자체에서 id 값을 생성하기 때문에 사용
    private Long id;

    private String name;


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
