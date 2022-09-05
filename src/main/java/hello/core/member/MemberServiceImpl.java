package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//이것만 붙이면 알아서 빈 등록이 된다. 컴포넌트 스캔을 할 예정
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
                                                        //구현체 설정, 구현체에 의존한다.
                                                        //DIP 위반

    private final MemberRepository memberRepository;
    //어플리케이션 컨피규레이션에서 지정한 구현체가 이걸 타고 들어온다.

    @Autowired//생성자에 붙여주면 스프링이 의존관계 주입을 자동으로 해준다. ac.getBean(MemberRepository.class)와 같다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//생성자로 주입한다.
    //생성자 주입, Injection


    @Override
    public void join(Member member) {
            memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }


}
