package bigtech.fbai.member.app;

import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import bigtech.fbai.member.app.dto.GetMemberResponseDto;
import bigtech.fbai.member.app.dto.UpdateMemberRequestDto;
import bigtech.fbai.member.dao.MemberRepository;
import bigtech.fbai.member.dao.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Transactional
    public GetMemberResponseDto updateMember(Long memberId, UpdateMemberRequestDto dto) {
        Member member = findMember(memberId);
        member.update(dto.nickname(), dto.imageUrl(), dto.introduce());

        return new GetMemberResponseDto(member.getId(), member.getNickname(), member.getImageUrl(),
            member.getIntroduce());
    }

    public GetMemberResponseDto getMember(Long memberId) {
        Member member = findMember(memberId);

        return new GetMemberResponseDto(member.getId(), member.getNickname(), member.getImageUrl(), member.getIntroduce());
    }
}
