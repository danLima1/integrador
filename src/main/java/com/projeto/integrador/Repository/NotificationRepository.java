package com.projeto.integrador.Repository;



import com.projeto.integrador.Entity.User;
import com.projeto.integrador.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(User loggedInUser);

    List<Notification> findByRecipientAndIsRead(User user, boolean b);
}