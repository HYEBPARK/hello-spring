package hello.hellospring;

import hello.hellospring.repository.MemberRepostiroy;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberRepostiroy memberRepostiroy(){
        return new MemoryMemberRepository();
    }
}
