package io.awportfoiioapi.notification.respotiroy.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.awportfoiioapi.notification.entity.Notification;
import io.awportfoiioapi.notification.entity.QNotification;
import io.awportfoiioapi.notification.respotiroy.query.NotificationQueryRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static io.awportfoiioapi.notification.entity.QNotification.*;

@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationQueryRepository {
    
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;
    
    @Override
    public List<Notification> findByOptionsId(Long id) {
        return queryFactory
                   .selectFrom(notification)
                   .where(notification.options.id.eq(id))
                   .fetch();
    }
    
    @Override
    public Long deleteByOptionsId(Long id) {
        em.flush();
        long execute = queryFactory
                .delete(notification)
                .where(notification.options.id.eq(id))
                .execute();
        em.clear();
        return execute;
    }
}
