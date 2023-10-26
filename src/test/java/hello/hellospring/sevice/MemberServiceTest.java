package hello.hellospring.sevice;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository;

    //테스트가 실행 되기 전
    @BeforeEach
    public void beforeEach() {
        //테스트가 실행 될때마다 독립적으로 각각 생성해준다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        //각 메서드가 끝날때마다 afterEach메서드가 호출된다.
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get(); //변수할당 단축키 ctrl+alt+v
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //"() -> memberService.join(member2)" 이러한 로직을 실행하면 llegalStateException 예외가 발생해야한다라는 걸 명시
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
/*

        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}