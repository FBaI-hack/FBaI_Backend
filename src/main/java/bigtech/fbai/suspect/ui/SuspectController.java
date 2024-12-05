package bigtech.fbai.suspect.ui;

import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.suspect.app.SuspectService;
import bigtech.fbai.suspect.app.dto.request.SuspectCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
