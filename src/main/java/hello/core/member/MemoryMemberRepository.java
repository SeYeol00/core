package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component//이것만 붙이면 알아서 빈 등록이 된다. 컴포넌트 스캔을 할 예정
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new ConcurrentHashMap<>();
                                                //동시성을 해결한 hashmap


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
