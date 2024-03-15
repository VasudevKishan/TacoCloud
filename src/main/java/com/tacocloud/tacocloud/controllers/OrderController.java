package com.tacocloud.tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tacocloud.tacocloud.data.OrderRepository;
import com.tacocloud.tacocloud.model.TacoOrder;

import jakarta.validation.Valid;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors,
      SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    orderRepo.save(order);
    sessionStatus.setComplete();
    return "redirect:/";
  }

}
