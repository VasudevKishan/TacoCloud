package com.tacocloud.tacocloud.data;

import org.springframework.data.repository.CrudRepository;

import com.tacocloud.tacocloud.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
  // TacoOrder save(TacoOrder order);
}
