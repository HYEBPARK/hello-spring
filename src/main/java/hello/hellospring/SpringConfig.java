package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepostiroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepostiroy memberRepostiroy(){
        // return new MemoryMemberRepository():  메모리 사용
        //return new JdbcMemberRepository(dataSource); db 사용
        return new JdbcTemplateMemberRepository(dataSource); // jdbc template 사용
    }
}
