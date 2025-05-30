package ptit.example.btlwebbook.dto.request;

import lombok.Getter;
import ptit.example.btlwebbook.contraints.EnumPattern;
import ptit.example.btlwebbook.utils.UserStatus;
@Getter
public class UserStatusDTO {
    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;
}
