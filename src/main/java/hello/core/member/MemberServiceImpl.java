package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // MemberServiceImpl이 구현체를 선택해야하는 책임을 덜기 위해 생성자를 이용해 외부에서 구현체를 설정.
    // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
