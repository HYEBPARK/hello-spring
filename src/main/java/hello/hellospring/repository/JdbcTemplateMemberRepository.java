package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepostiroy{
    // jdbc 사용시 중복되는 코드를 줄일 수 있다.
    // 하지만 sql문을 직접 입력해줘야한다.
    // datasource는 db 커넥션 정보를 가지고 있고 bean으로 등록하여 인자로 넘겨준다.

    private final JdbcTemplate jdbcTemplate;

    @Autowired // 생성자가 1개면 autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String , Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result =  jdbcTemplate.query("select * from member where id = ?",memberRowMapper(), id );
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  jdbcTemplate.query("select * from member where name = ?",memberRowMapper(),name );
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }


    // RoewMapper 사용시 원하는 형태의 결가값을 반환할 수 있다.
    // rs 값을 Member 객체에 저장한다.
    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}