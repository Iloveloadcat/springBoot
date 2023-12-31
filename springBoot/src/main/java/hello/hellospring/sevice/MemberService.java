package hello.hellospring.sevice;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Service
@Transactional
public class MemberService {

    //@Autowired
    private MemberRepository memberRepository = new MemoryMemberRepository();

    public MemberService(MemberRepository memberRepository) {
        //객체의 밖에서 객체를 넣어주는(주입) 방식이 의존성 주입
        //new하지 않고 외부에서 넣어줌. => DI(Dependency Injection)
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x

        /*
        Optional<Member> result = memberRepository.findByName(member.getName()); //ctrl+alt+v 하면 변수명 자동 생성
        //null을 반환하면서 발생되는 오류를 줄이고자 ifPresent 사용
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        */
        //좀더 간단하게 아래와 같이 작성도 가능
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                }); //=>메소드로 따로 작성하는것이 좋음 (ctrl+alt+shift+t)
    }

    /***
     * 전체회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
