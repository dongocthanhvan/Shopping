package com.example.web.controller;

import com.example.web.entity.OrderDetail;
import com.example.web.entity.Orders;
import com.example.web.repository.OrderDetailRepository;
import com.example.web.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;


    @RequestMapping("")
    public String getOrderList(Model model) {
      List<Orders> orders = orderRepository.findAll();
      List<OrderDetail> orderDetail = orderDetailRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("orderDetail", orderDetail);
        return "orderList";
    }
    @RequestMapping("/{id}")
    public String getOrderByID(Model model, @PathVariable(value = "id") Long id) {
        Orders order = orderRepository.getById(id);
        List<OrderDetail> orderDetail = orderDetailRepository.findByOrders_Id(id);
        model.addAttribute("orders", order);
        model.addAttribute("orderDetail", orderDetail);
        return "orderInfo";
    }
}

