package com.devmatheus.vendas.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmatheus.vendas.entities.Order;
import com.devmatheus.vendas.entities.OrderItem;
import com.devmatheus.vendas.entities.Payment;
import com.devmatheus.vendas.entities.Product;
import com.devmatheus.vendas.entities.User;
import com.devmatheus.vendas.entities.dto.OrderItemDto;
import com.devmatheus.vendas.entities.enums.OrderStatus;
import com.devmatheus.vendas.repositories.OrderItemRepository;
import com.devmatheus.vendas.repositories.OrderRepository;
import com.devmatheus.vendas.services.exceptions.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private OrderItemRepository itemRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ProductService productService;

  public Order create(List<OrderItemDto> items) {
    // TODO: fazer autenticação para pegar user logado
    User user = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
    user = userService.create(user);

    Order order = new Order();
    order.setClient(user);
    order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
    order.setMoment(Instant.now());
    order = repository.save(order);

    for (OrderItemDto item : items) {
      Product product = productService.findById(item.getProductId());
      itemRepository.save(new OrderItem(order, product, item.getQuantity(), product.getPrice()));
    }
    return repository.save(order);
  }

  public OrderStatus updateStatus(Long orderId, OrderStatus status) {
    try {
      Order order = repository.getReferenceById(orderId);
      order.setOrderStatus(status);
      repository.save(order);
      return status;
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(orderId);
    }
  }

  public void pay(Long orderId) {
    try {
      Order order = repository.getReferenceById(orderId);
      order.setPayment(new Payment(null, Instant.now(), order));
      repository.save(order);
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(orderId);
    }
  }

  public List<Order> findAll() {
    return repository.findAll();
  }

  public Order findById(Long id) {
    Optional<Order> user = repository.findById(id);
    return user.get();
  }
}
