package ru.orderservice.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.orderservice.enums.OrderStatus;
import ru.orderservice.models.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByIdAndUserId(long id, long userId);

    @Modifying
    @Transactional
    @Query("UPDATE orders o SET o.status = :status WHERE o.id = :orderId")
    void updateStatusById(OrderStatus status, long orderId);

    Optional<Order> findByIdAndUserIdAndStatus(long id, long userId, OrderStatus status);

}
