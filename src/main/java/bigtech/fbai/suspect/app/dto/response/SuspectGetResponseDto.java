package bigtech.fbai.suspect.app.dto.response;

import bigtech.fbai.suspect.dao.entity.Suspect;

public record SuspectGetResponseDto(Long suspectId, String name, String email,
                                    String bank, String account, String platform) {

    public static SuspectGetResponseDto from(Suspect suspect) {
        return new SuspectGetResponseDto(suspect.getSuspectId(), suspect.getName(), suspect.getEmail(),
                suspect.getBank(), suspect.getAccount(), suspect.getPlatform());
    }
}
