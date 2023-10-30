package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //hello.hellospring 패키지 하위에 전부 적용
    //AOP 설정 클래스를 빈으로 직접 등록할 때는 어떻게 문제를 해결하면 될까요?
    //바로 다음과 같이 AOP 대상에서 SpringConfig를 빼주면 됩니다.
    //@Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    //또는 @Component 삭제후 application.properties에 spring.main.allow-bean-definition-overriding=true 추가
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start : " + joinPoint.toString());
        try {
            return joinPoint.proceed(); //inline => ctrl+alt+n
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("end : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
