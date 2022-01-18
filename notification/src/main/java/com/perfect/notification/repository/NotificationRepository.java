package com.perfect.notification.repository;

import com.perfect.notification.model.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<SmsNotification, Integer> {
}
