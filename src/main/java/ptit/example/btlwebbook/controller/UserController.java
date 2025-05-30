package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ptit.example.btlwebbook.dto.request.*;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.dto.response.UserResponse;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.service.UserService;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;
    String ERROR_MESSAGE = "errorMessage: {} ";

        @PostMapping("/staff")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseData<UserResponse> createUser(@Valid @RequestBody UserRequestDTO requestDTO) throws IOException {
                UserResponse user = userService.saveUser(requestDTO);
                return new ResponseData<>(HttpStatus.CREATED.value(), "success", user);

        }

    @GetMapping("/{id}")
//    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<UserResponse> getDetailUser(@PathVariable long id) {
        try {
            UserResponse userResponse = userService.getDetailUser(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success", userResponse);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseData<UserResponse> updateUser(@PathVariable long id, @RequestBody @Valid UpdateUserDTO userRequestDTO) {
        try {
            UserResponse userResponse = userService.updateUser(id, userRequestDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", userResponse);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<UserResponse> changeStatus(@PathVariable long id, @RequestBody @Valid UserStatusDTO status) {
        try {
            UserResponse userResponse = userService.changeStatus(id, status.getStatus());
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", userResponse);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<Void> deleteUser(@PathVariable long id){
        try {
            userService.deleteUser(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "success");
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<PageResponse<?>> getAllUser(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                      @RequestParam(defaultValue = "8", required = false) int pageSize,
                                      @RequestParam(defaultValue = "createdAt:desc", required = false) String sortBy,
                                      @RequestParam(required = false) String query,
                                      @RequestParam(required = false) ROLE role){
        try {
            PageResponse<?> pageResponse = userService.getAllUser(pageNo, pageSize, sortBy, query, role);
            return new ResponseData<>(HttpStatus.OK.value(), "success", pageResponse);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PatchMapping("/{id}/avatar")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<UserResponse> updateAvatar(@PathVariable long id, @RequestBody @Valid ImageDTO imageDTO) {
        try {
            UserResponse userResponse = userService.updateAvatar(id, imageDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", userResponse);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
