package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepostiroy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional // transactional 사용시 test 수행 후 rollback을 해준다. 이는 db에 데이터가 남지 않아 다음 테스트에 영향을 주지 않는다.

class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired  MemberRepostiroy memberRepostiroy;

    @Test
    void join() {
        // given 무언가가 주어졌을때
        Member member1 = new Member();
        member1.setName("baby");
        // when 실행했을때
        Long saveId = memberService.join(member1);
        // then 결과가 이렇게 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplication(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    public void findOne() {
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);
        Member findId = memberService.findOne(member1.getId()).get();
        assertThat(member1.getId()).isEqualTo(findId.getId());
    }
}