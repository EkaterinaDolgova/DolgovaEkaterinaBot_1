package pro.DolgovaEkaterinaBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pro.DolgovaEkaterinaBot.model.Notification;

import java.util.Collection;
import java.util.List;

@Service
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Collection<Notification> findByTextY(Long Id);

    List<Notification> findByDatetime();
}
