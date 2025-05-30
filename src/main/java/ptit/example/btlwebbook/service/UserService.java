package ptit.example.btlwebbook.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import ptit.example.btlwebbook.dto.request.ImageDTO;

import ptit.example.btlwebbook.dto.request.UpdateUserDTO;
import ptit.example.btlwebbook.dto.request.UserRequestDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.UserResponse;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;


import java.io.IOException;

public interface UserService {
    UserDetailsService userDetailsService();

    User getByEmail(String email);

    UserResponse saveUser(UserRequestDTO userRequestDTO) throws IOException;

    UserResponse updateUser(long userId, UpdateUserDTO request) throws IOException;

    UserResponse changeStatus(long userId, UserStatus request);

    void deleteUser(long userId);

    UserResponse getDetailUser(long id);

    PageResponse<?> getAllUser(int pageNo, int pageSize, String sortBy, String query, ROLE role);

    UserResponse updateAvatar(long userId, ImageDTO imageDTO) throws IOException;
}
