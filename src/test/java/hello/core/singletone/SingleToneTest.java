package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTest {

    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService = " + memberService1);
        System.out.println("MemberService = " + memberService2);

        //계속 객체를 선언해서 제공해준다. 너무 많은 객체가 생성되면 비효울적!

        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);

    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleToneService(){
        SingleToneService singleToneService1 = SingleToneService.getInstance();
        SingleToneService singleToneService2 = SingleToneService.getInstance();

        Assertions.assertThat(singleToneService1).isSameAs(singleToneService2);

        //same ==
        //isEqual equals()
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContanier(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService = " + memberService1);
        System.out.println("MemberService = " + memberService2);



        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
