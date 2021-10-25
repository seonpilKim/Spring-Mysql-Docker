package study.springmysqldocker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public void join(Member member, BindingResult result) {
        boolean isDuplicated = validateDuplicateMember(member, result);
        if (!isDuplicated) {
            memberRepository.save(member);
        }
    }

    private boolean validateDuplicateMember(Member member, BindingResult result) {
        Optional<Member> findMember = memberRepository.findMemberByName(member.getName());
        if (findMember.isPresent()) {
            result.addError(new FieldError("member", "memberName", "이미 존재하는 회원입니다."));
            return true;
        }
        return false;
    }
}
