package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component//이것만 붙이면 알아서 빈 등록이 된다. 컴포넌트 스캔을 할 예정
public class RateDiscountPolicy implements DiscountPolicy{

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
