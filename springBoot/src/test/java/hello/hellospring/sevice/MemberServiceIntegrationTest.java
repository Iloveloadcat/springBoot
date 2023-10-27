package hello.hellospring.sevice;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링컨테이너와 함께 테스트를 실행함.
@Transactional //실제 DB에 커밋되지않고 테스트 실행 전 트랜젝션을 시작하고 ,테스트 실행 후 rollback됨.
// 따라서 테스트를 반복적으로 실행할 수있음.
class MemberServiceIntegrationTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //new를통해 직접 객체를 생성하면 각각의 클래스에 의존하게 된다. 다른 객체를 사용하고싶어도 코드를 수정하기전까진 불가능.
    //이러한 의존성을 없애기위해 의존성을 주입시키는 방식을 사용함.

    @Autowired MemberService memberService;

    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit //이걸쓰면 실제 DB에 반영됨
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
        //then
/*

        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
*/

    }

}