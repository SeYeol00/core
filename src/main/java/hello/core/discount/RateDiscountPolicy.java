package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component//이것만 붙이면 알아서 빈 등록이 된다. 컴포넌트 스캔을 할 예정
//@Qualifier("mainDiscountPolicy")
//@Primary//중복되는 타입의 빈들이 있으면 이걸 붙이면 이게 우선적으로 매칭된다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{
// @Qualifier vs @Primary 의 우선 순위는 전자가 더 높다.
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
