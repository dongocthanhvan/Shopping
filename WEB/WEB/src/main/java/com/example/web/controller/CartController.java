package com.example.web.controller;

import com.example.web.entity.Item;
import com.example.web.entity.OrderDetail;
import com.example.web.entity.Orders;
import com.example.web.entity.Shop;
import com.example.web.repository.OrderDetailRepository;
import com.example.web.repository.OrderRepository;
import com.example.web.repository.ProductDetailRepository;
import com.example.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @RequestMapping("")
    public String index(){
////        model.addAttribute("total",sum(session));
//        List<Item> cart = (List<Item>) session.getAttribute("cart");
//        session.setAttribute("cart",cart);
//        model.addAttribute("total",sum(session));
        return "cartList";
    }

    @RequestMapping("/buy/{id}")
    public String buy(Model model,
                      @PathVariable("id") Long id,
                      HttpSession session)
    {
        if(session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productRepository.getById(id),1));
            session.setAttribute("cart",cart);
            model.addAttribute("total",sum(session));
            return "cartList";
        }else{
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id,cart);
            if(index == -1){
                cart.add(new Item(productRepository.getById(id),1));
            }else{
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart",cart);
            model.addAttribute("total",sum(session));
            return "cartList";
        }

    }

    @RequestMapping("/remove/{id}")
    public String removeItem(Model model,
                             @PathVariable("id") Long id,
                             HttpSession session)
    {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExist(id,cart);
        cart.remove(index);
        session.setAttribute("cart",cart);
        model.addAttribute("total",sum(session));
        return "cartList";
    }

    @RequestMapping(value = "/update")
    public String updateItem(Model model, HttpServletRequest request,
                             HttpSession session, String quantity)
    {
        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for(int i = 0 ; i < cart.size() ; i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart",cart);
        model.addAttribute("total",sum(session));
        return "cartList";
    }

    @RequestMapping("/checkout")
    public String checkout(HttpSession session, Model model, HttpServletRequest request){
            //add new order
//            Shop shop = new Shop();
//            model.addAttribute("shop", shop);
            Orders orders = new Orders();

//            orders.setCustomer(customerRepository.findByUsernameContaining(session.getAttribute("username").toString()));
            String customer_name = request.getParameter("customer_name");
            String customer_address = request.getParameter("customer_address");
            String customer_phone = request.getParameter("customer_phone");
            String customer_email = request.getParameter("customer_email");
            orders.setCustomer_name(customer_name);
            orders.setCustomer_phone(customer_phone);
            orders.setCustomer_address(customer_address);
            orders.setCustomer_email(customer_email);
            orders.setDate(new Date());
            orders.setName("New Order");
            double totalPrice = sum(session);
            orders.setTotalPrice(totalPrice);
            Long orderId = orderRepository.save(orders).getId();
            //add order detail
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            session.setAttribute("cart",cart);
            for(Item itemCart : cart){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(itemCart.getProduct());
                orderDetail.setPrice(itemCart.getProduct().getPrice()*itemCart.getQuantity());
                orderDetail.setQuantity(itemCart.getQuantity());
                orderDetail.setOrders(orders);
                orderDetailRepository.save(orderDetail);
            }
            //delete cart
            session.removeAttribute("cart");
            return "orderSuccess";

    }

    private  int isExist(Long id, List<Item> carts){
        for(int i = 0; i < carts.size(); i++){
            if(carts.get(i).getProduct().getId() == id){
                return  i;
            }
        }
        return -1;
    }

    public double sum(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s = 0;
        for(Item item : cart){
            s += item.getQuantity() * item.getProduct().getPrice();
        }
        return s;
    }
}


