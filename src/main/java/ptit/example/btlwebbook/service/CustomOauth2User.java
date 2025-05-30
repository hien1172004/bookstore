package ptit.example.btlwebbook.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ptit.example.btlwebbook.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class CustomOauth2User implements OAuth2User {
    // Getter cho đối tượng User (nếu cần truy cập thông tin người dùng từ cơ sở dữ liệu)
    @Getter
    private final User user;
    private final Map<String, Object> attributes;

    public CustomOauth2User(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về danh sách quyền (roles) của người dùng
        // Ví dụ: Gán quyền dựa trên role trong đối tượng User
        return Collections.singletonList(() -> "ROLE_" + user.getRole().name());
    }

    @Override
    public String getName() {
        // Trả về tên hoặc email của người dùng
        return user.getFullName() != null ? user.getFullName() : user.getEmail();
    }

    // Các phương thức bổ sung nếu cần
    public String getEmail() {
        return user.getEmail();
    }

    public String getServiceId() {
        return user.getServiceId();
    }
}
