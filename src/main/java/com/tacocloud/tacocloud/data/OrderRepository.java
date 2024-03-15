package com.tacocloud.tacocloud.data;

import com.tacocloud.tacocloud.model.TacoOrder;

public interface OrderRepository {
  TacoOrder save(TacoOrder order);
}
