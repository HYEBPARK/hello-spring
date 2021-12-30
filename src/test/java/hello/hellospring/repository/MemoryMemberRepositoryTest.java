package hello.hellospring.repository;
// 개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나 , 웹 애프리케이션의 컨트롤러를 통해서 해당 기능을 실행한다.
// 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는 단점이 있다.
// 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository repostiroy = new MemoryMemberRepository();


    // test할때 메소드 순서를 보장하지 않기때문에
    // test 하나가 끝날 경우 메모리를 clean 해줘야함 !
    @AfterEach // 메소드가 끝날때마다 호출이 됨
    public void afterEach(){
        repostiroy.clearStore();
    }



    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repostiroy.save(member);

        Member result = repostiroy.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // import static org.assertj.core.api.Assertions.assertThat;
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repostiroy.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repostiroy.save(member2);

        Member result = repostiroy.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repostiroy.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repostiroy.save(member2);

        List<Member> result = repostiroy.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


}