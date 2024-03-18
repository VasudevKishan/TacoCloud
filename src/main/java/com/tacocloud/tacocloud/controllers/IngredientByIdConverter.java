package com.tacocloud.tacocloud.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.tacocloud.tacocloud.data.IngredientRepository;
import com.tacocloud.tacocloud.model.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
  // private Map<String, Ingredient> ingredientMap = new HashMap<>();
  private IngredientRepository ingredientRepo;

  // @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @Override
  public Ingredient convert(String id) {
    return ingredientRepo.findById(id).orElse(null);
  }

}
