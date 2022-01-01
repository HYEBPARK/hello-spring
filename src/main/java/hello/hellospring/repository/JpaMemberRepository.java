package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// 중복되는 코드를 제거해주고
// jdbc template에서 sql 을 직접 입력해줘야하는 불편함도 해결해줌
// 생산성 향상 !
public class JpaMemberRepository implements MemberRepostiroy{

    private final EntityManager em;
    public JpaMemberRepository(EntityManager em){
        this.em = em ;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체(entity)를 대상으로 쿼리를 날린다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
