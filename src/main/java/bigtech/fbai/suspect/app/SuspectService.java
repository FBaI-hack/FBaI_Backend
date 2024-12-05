package bigtech.fbai.suspect.app;

import bigtech.fbai.suspect.app.dto.request.SuspectCreateRequestDto;
import bigtech.fbai.suspect.dao.SuspectRepository;
import bigtech.fbai.suspect.dao.entity.Suspect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SuspectService {

    private final SuspectRepository suspectRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Suspect createSuspect(SuspectCreateRequestDto suspectCreateRequestDto) {

        String name = suspectCreateRequestDto.name();
        String email = suspectCreateRequestDto.email();
        String bank = suspectCreateRequestDto.bank();
        String account = suspectCreateRequestDto.account();
        String platform = suspectCreateRequestDto.platform();

        Suspect newSuspect = Suspect.create(name, email, bank, account, platform);

        return suspectRepository.save(newSuspect);
    }

    @Transactional
    public Suspect getSuspect(String name, String email, String bank, String account, String platform) {
        Suspect suspect = suspectRepository.findBySuspectInfo(name,email,bank,account,platform);
        suspect.countIncrement();
        return suspect;
    }

}
