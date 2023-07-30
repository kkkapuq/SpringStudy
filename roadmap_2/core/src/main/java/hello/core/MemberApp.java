package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

public class MemberApp {
    public static void main(String[] args) {

        // 자바로 생성하는 방식
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // 이렇게 하면 DIP 위반.
//        MemberService memberService = new MemberServiceImpl();

        // 이렇게 하면 AppConfig에 있는 환경설정 정보를 가지고 구성요소들을 스프링 Bean에서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

        @Controller
    }
}
