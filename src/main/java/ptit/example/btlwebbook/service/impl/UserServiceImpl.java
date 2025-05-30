package ptit.example.btlwebbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ptit.example.btlwebbook.dto.request.ImageDTO;
import ptit.example.btlwebbook.dto.request.SignUpDTO;
import ptit.example.btlwebbook.dto.request.UpdateUserDTO;
import ptit.example.btlwebbook.dto.request.UserRequestDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.UserResponse;
import ptit.example.btlwebbook.exception.EmailAlreadyExistsException;
import ptit.example.btlwebbook.exception.InvalidDataException;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.UserMapper;
import ptit.example.btlwebbook.model.Avatar;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.service.UploadImageFile;
import ptit.example.btlwebbook.service.UserService;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.SlugUtils;
import ptit.example.btlwebbook.utils.UserStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private  final UserMapper userMapper;

    private final UploadImageFile cloudFile;


    @Override
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("email not found"));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("email not found"));
    }

    @Override
    @Transactional
    public UserResponse saveUser(UserRequestDTO request) throws IOException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = request.getEmail();
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException("email ton tai");
        }
        User user= userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRole(ROLE.STAFF);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        log.info("save userId: {}", user.getId());
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(long userId, UpdateUserDTO request) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userMapper.updateUser(user, request);
        User updatedUser = userRepository.save(user);
        log.info("update userid {}", user.getId());
        return userMapper.toUserResponse(updatedUser);
    }



    @Override
    @Transactional
    public UserResponse changeStatus(long userId, UserStatus request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setStatus(request);
        User updatedUser = userRepository.save(user);
        log.info("change userId {} with status {}", user.getId(), request);
        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
        log.info("User has deleted permanent successfully, userId={}", userId);
    }

    @Override
    public UserResponse getDetailUser(long id) {
        User user= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found user"));
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public PageResponse<?> getAllUser(int pageNo, int pageSize, String sortBy, String query, ROLE role) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo - 1;
        }
        List<Sort.Order> sorts = new ArrayList<>();
        if(StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else if (matcher.group(3).equalsIgnoreCase("desc")) {
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));
        Page<User> pageUser;
        if (StringUtils.hasLength(query) && StringUtils.hasLength(role.toString())) {
            pageUser = userRepository.findByQueryAndRole(query, role , pageable);
        } else if (StringUtils.hasLength(query)) {
            pageUser = userRepository.getUsersByQuery(query, pageable);
        } else if (StringUtils.hasLength(toString())) {
            pageUser = userRepository.findByRole(role, pageable);
        } else {
            pageUser = userRepository.findAll(pageable);
        }
        List<UserResponse> users = pageUser.stream().map(userMapper::toUserResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(pageUser.getTotalPages())
                .items(users)
                .build();
    }

    @Override
    public UserResponse updateAvatar(long userId, ImageDTO imageDTO) throws IOException {
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found user"));
        if(!user.getAvatar().getPublicId().isEmpty()){
            cloudFile.deleteImage(imageDTO.getPublicId());
        }
        user.getAvatar().setPublicId(imageDTO.getPublicId());
        user.getAvatar().setUrl(imageDTO.getImageUrl());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }


}
