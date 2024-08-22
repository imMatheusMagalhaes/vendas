package com.devmatheus.vendas.services;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmatheus.vendas.entities.Order;
import com.devmatheus.vendas.entities.OrderItem;
import com.devmatheus.vendas.entities.Payment;
import com.devmatheus.vendas.entities.Product;
import com.devmatheus.vendas.entities.User;
import com.devmatheus.vendas.entities.dto.CreateOrderDto;
import com.devmatheus.vendas.entities.dto.UpdateOrderStatusDto;
import com.devmatheus.vendas.entities.enums.OrderStatus;
import com.devmatheus.vendas.repositories.OrderItemRepository;
import com.devmatheus.vendas.repositories.OrderRepository;
import com.devmatheus.vendas.services.exceptions.NotFoundException;
import com.devmatheus.vendas.services.exceptions.UnauthorizedException;

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

  public Order create(List<CreateOrderDto> items) {
    // TODO: fazer autenticação para pegar user logado
    User user = userService.findById(1L);

    Order order = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, user);
    order = repository.save(order);
    Set<OrderItem> orderItemSet = new HashSet<>();

    for (CreateOrderDto item : items) {
      Product product = productService.findById(item.productId());
      OrderItem orderItem = new OrderItem(order, product, item.quantity(), product.getPrice());
      orderItemSet.add(orderItem);
      order.setItem(orderItem);
    }
    itemRepository.saveAll(orderItemSet);
    return order;
  }

  public OrderStatus updateStatus(Long orderId, UpdateOrderStatusDto dto) {
    try {
      if (dto.status() == OrderStatus.PAID) {
        throw new UnauthorizedException();
      }
      Order order = repository.getReferenceById(orderId);
      order.setOrderStatus(dto.status());
      repository.save(order);
      return dto.status();
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(orderId);
    }
  }

  public void pay(Long orderId) {
    try {
      Order order = repository.getReferenceById(orderId);
      order.setPayment(new Payment(null, Instant.now(), order));
      order.setOrderStatus(OrderStatus.PAID);
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
    return user.orElseThrow(() -> new NotFoundException(id));
  }
}
