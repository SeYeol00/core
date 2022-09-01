package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
                                                        //구현체 설정, 구현체에 의존한다.
                                                        //DIP 위반

    private final MemberRepository memberRepository;
    //어플리케이션 컨피규레이션에서 지정한 구현체가 이걸 타고 들어온다.

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
}
