package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        // 이렇게 하면 각 테스트를 실행하기 전에 같은 메모리 리포지토리를 사용하게 된다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 이렇게 하면 서로 다른 인스턴스의 레파지토리를 사용하게 된다.
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afrterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        //이런거 이름 다 한번에 바꾸고싶으면 shift + f6
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 이 익셉션이 터져야되고, 터질 때 -> 로직으로 들어간다는걸 람다로 표현한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 이런식으로 try catch로 테스트를 잡아낼 수 있지만, 부적절하다. 이쁘게 쓰는 방법은 위와 같다.
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123");
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