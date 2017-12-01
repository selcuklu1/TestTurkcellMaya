package common;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 28.11.2017
 * Açıklama:
 */
@SuppressWarnings("unused")
@Aspect
public class CustomAspect {

/*    private static AllureLifecycle ALLURE = Allure.getLifecycle();


    @Pointcut("execution(* com.automation.remarks.video.service.pages.*.*(..))")
    public void anyMethod() {
        //pointcut body, should be empty
    }

    @Pointcut("execution(* com.codeborne.selenide.SelenideElement.should*(..))")
    public void selenide() {
        //pointcut body, should be empty
    }

    @Before("anyMethod() || selenide()")
    public void stepStart(JoinPoint joinPoint) {
        String stepTitle = createTitle(joinPoint);

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        StepStartedEvent startedEvent = new StepStartedEvent(
                getName(methodSignature.getName(), joinPoint.getArgs())
        );

        if (!stepTitle.isEmpty()) {
            startedEvent.setTitle(stepTitle);
        }

        ALLURE.fire(startedEvent);
    }

    @AfterThrowing(pointcut = "anyMethod() || selenide()", throwing = "e")
    public void stepFailed(JoinPoint joinPoint, Throwable e) {
        ALLURE.fire(new StepFailureEvent().withThrowable(e));
        ALLURE.ffire(new StepFinishedEvent());
    }

    @AfterReturning(pointcut = "anyMethod() || selenide()", returning = "result")
    public void stepStop(JoinPoint joinPoint, Object result) {
        ALLURE.fire(new StepFinishedEvent());
    }

    public String createTitle(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Step step = methodSignature.getMethod().getAnnotation(Step.class);
        return step == null ? "" : getTitle(step.value(), methodSignature.getName(), joinPoint.getThis(), joinPoint.getArgs());
    }


    *//**
     * For tests only
     *//*
    static void setAllure(Allure allure) {
        CustomAspect.ALLURE = allure;
    }*/
}
