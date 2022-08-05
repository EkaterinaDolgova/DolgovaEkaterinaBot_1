package pro.DolgovaEkaterinaBot.service;

import org.springframework.stereotype.Service;
import pro.DolgovaEkaterinaBot.repository.NotificationRepository;
import pro.DolgovaEkaterinaBot.model.Notification;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification creatNotification(Notification notification) {
        System.out.println("Попали в сохранение");
        return notificationRepository.save(notification);
    }

    public List<Notification> findByDatetime() {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Notification> notifications = notificationRepository.findAll().stream()
                .filter(s -> s.getDatetime().equals(currentTime))
                .collect(Collectors.toList());
        return notifications;
    }
}

