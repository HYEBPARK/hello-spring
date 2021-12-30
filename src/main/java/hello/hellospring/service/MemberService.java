package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepostiroy;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepostiroy memberRepostiroy;

    // DI
    public MemberService(MemberRepostiroy memberRepostiroy) {
        this.memberRepostiroy = memberRepostiroy;
    }


    // 회원 가입
    //ifPresent : null 이 아니라면
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepostiroy.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepostiroy.findByName(member.getName())
           .ifPresent(m -> {
               throw new IllegalStateException("이미 존재하는 회원입니다.");
           });
    }

    public List<Member> findMembers(){
        return memberRepostiroy.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepostiroy.findById(memberId);
    }


}
