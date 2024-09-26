package ru.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orderservice.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
