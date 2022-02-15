package com.example.web.repository;
import com.example.web.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrders(Long id);
    List<OrderDetail> findByOrders_Id(Long id);

}
