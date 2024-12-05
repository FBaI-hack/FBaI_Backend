package bigtech.fbai.member.ui;

import bigtech.fbai.common.annotation.Auth;
import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.member.app.MemberService;
import bigtech.fbai.member.app.dto.GetMemberResponseDto;
import bigtech.fbai.member.app.dto.UpdateMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PatchMapping
    public ResponseDto<GetMemberResponseDto> updateMember(@Auth Long memberId,
        @RequestBody UpdateMemberRequestDto dto) {
        return ResponseDto.ok(memberService.updateMember(memberId, dto));
    }

    @GetMapping
    public ResponseDto<GetMemberResponseDto> getMember(@Auth Long memberId) {
        return ResponseDto.ok(memberService.getMember(memberId));
    }
}
