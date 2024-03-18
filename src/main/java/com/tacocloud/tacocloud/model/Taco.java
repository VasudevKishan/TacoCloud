package com.tacocloud.tacocloud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table
public class Taco {

  @Id
  private long id;

  private Date createdAt = new Date();

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  @NotNull
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<IngredientRef> ingredients = new ArrayList<>();

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(new IngredientRef(ingredient.getId()));
  }
}
