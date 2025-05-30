package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.UpdateUserDTO;
import ptit.example.btlwebbook.dto.request.UserRequestDTO;
import ptit.example.btlwebbook.dto.response.UserResponse;
import ptit.example.btlwebbook.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDTO userRequestDTO);
    void updateUser(@MappingTarget User user, UpdateUserDTO updateUserDTO);

    UserResponse toUserResponse(User user);
}
