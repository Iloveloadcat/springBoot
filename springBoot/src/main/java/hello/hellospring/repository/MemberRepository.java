package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    //Optional은 자바8 문법으로서 NPE발생을 방지해주는 역할
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
