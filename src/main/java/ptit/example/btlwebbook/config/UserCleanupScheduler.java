package ptit.example.btlwebbook.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.utils.UserStatus;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCleanupScheduler {
    private final UserRepository userRepository;



    // Xóa người dùng chưa xác thực sau 24 giờ
    @Scheduled(fixedRate = 86400000) // Chạy mỗi 24 giờ
    public void cleanUpUnverifiedUsers() {
        Date twentyFourHoursAgo = new Date(System.currentTimeMillis() - 86400000); // 24 giờ trước
        List<User> usersToDelete = userRepository.findByStatusAndCreatedAtBefore(UserStatus.NONE, twentyFourHoursAgo);
        for (User user : usersToDelete) {
            userRepository.delete(user);
            log.info("Đã xóa người dùng chưa xác thực với email: {}", user.getEmail());
        }
    }
}
