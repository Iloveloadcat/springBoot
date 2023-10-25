package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional로 wrapper해줬기 때문에 null값이 있어도 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        //.stream() => 루프 돌면서 가져오는 메소드
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //파라미터로 받은 name이 같은경우에 filter
                .findAny(); //찾으면 반환
    }

    @Override
    public List<Member> findALl() {
        return new ArrayList<>(store.values()); //store에 있는 member들을 반환
    }

    public void clearStore() {
        store.clear();
    }
}
