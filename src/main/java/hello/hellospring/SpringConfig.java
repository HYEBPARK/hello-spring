package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepostiroy;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepostiroy());
    }

    @Bean
    public MemberRepostiroy memberRepostiroy(){
        // return new MemoryMemberRepository():  메모리 사용
        //return new JdbcMemberRepository(dataSource); db 사용
        // return new JdbcTemplateMemberRepository(dataSource); // jdbc template 사용
        return new JpaMemberRepository(em);
    }
}
