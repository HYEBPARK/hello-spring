package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void aftereach(){
        memoryMemberRepository.clearStore();
    }

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
    public void 중복_회원_예외(){
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
    public void findMembers() {
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);
        assertThat(1).isEqualTo(memberService.findMembers().size());
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