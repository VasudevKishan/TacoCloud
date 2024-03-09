package com.tacocloud.tacocloud.data;

import java.util.Optional;

import com.tacocloud.tacocloud.model.Ingredient;

public interface IngredientReporsitory {
  Iterable<Ingredient> findAll();

  Optional<Ingredient> findById(String id);

  Ingredient save(Ingredient ingredient);
}
