package com.tacocloud.tacocloud.model;

import lombok.Data;

@Data
public class Ingredient {
  public  final String id;
  public final String name;
  public final Type type;

  public enum Type{
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}
