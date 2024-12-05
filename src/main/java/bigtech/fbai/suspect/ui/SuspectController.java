package bigtech.fbai.suspect.ui;

import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.common.dto.ResponseDto;
import bigtech.fbai.suspect.app.SuspectService;
import bigtech.fbai.suspect.app.dto.request.SuspectCreateRequestDto;
import bigtech.fbai.suspect.app.dto.response.SuspectGetResponseDto;
import bigtech.fbai.suspect.dao.entity.Suspect;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suspect")
public class SuspectController {

    private final SuspectService suspectService;

    @PostMapping("")
    public CommonSuccessDto createSuspect(@RequestBody SuspectCreateRequestDto suspectCreateRequestDto){
        return suspectService.createSuspect(suspectCreateRequestDto);
    }

    @GetMapping("")
    public ResponseDto<Suspect> getSuspect(@RequestParam("name") String name, @RequestParam("email") String email,
                                           @RequestParam("bank") String bank, @RequestParam("account") String account, @RequestParam("platform") String platform){
        return ResponseDto.ok(suspectService.getSuspects(name,email,bank,account,platform));
    }
}
