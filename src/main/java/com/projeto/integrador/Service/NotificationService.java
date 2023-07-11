package com.projeto.integrador.Service;

import com.projeto.integrador.notification.Notification;
import com.projeto.integrador.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.integrador.Entity.User;

import java.util.List;
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    public List<Notification> getNotificationsForCurrentUser() {
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        return notificationRepository.findByRecipient(loggedInUser);
    }
    public void markNotificationsAsRead() {
        User user = userAuthenticationService.getLoggedInUser();

        if (user != null) {
            List<Notification> notifications = notificationRepository.findByRecipientAndIsRead(user, false);
            for (Notification notification : notifications) {
                notification.setRead(true);
                notificationRepository.save(notification);
            }
        }
    }
}