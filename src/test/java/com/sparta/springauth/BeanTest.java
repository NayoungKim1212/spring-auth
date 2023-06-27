package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {

    @Autowired// Bean 타입으로 DI를 지원, Food 타입으로 Bean을 찾음 --> 오류 발생 --> Bean의 이름으로 찾음
    @Qualifier("pizza")
    Food food;

    @Test@DisplayName("Primary 와 Qualifier 우선순위 확인")
    void test1() {
        food.eat();
    }
}
// Qualifier > Primary (우선순위)
// 같은 타입의 Bean이 여러 개 있을 때 범용적으로 사용되는 Bean 객체에는 Primary
// 지역적으로 사용되는 Bean 객체는 Qualifier 사용

// 좁은 범위의 설정이 우선 순위가 높음