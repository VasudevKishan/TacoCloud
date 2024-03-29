package com.tacocloud.tacocloud.controllers;

// import java.util.Arrays;
// import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;

import com.tacocloud.tacocloud.data.IngredientReporsitory;
import com.tacocloud.tacocloud.model.Ingredient;
import com.tacocloud.tacocloud.model.Ingredient.Type;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;

import com.tacocloud.tacocloud.model.Taco;
import com.tacocloud.tacocloud.model.TacoOrder;
// import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientReporsitory ingredientRepo;

  // @Autowired
  public DesignTacoController(IngredientReporsitory ingredientRepo) {
    this.ingredientRepo = ingredientRepo;

  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    // List<Ingredient> ingredients = Arrays.asList(
    // new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
    // new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
    // new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
    // new Ingredient("CARN", "Carnitas", Type.PROTEIN),
    // new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
    // new Ingredient("LETC", "Lettuce", Type.VEGGIES),
    // new Ingredient("CHED", "Cheddar", Type.CHEESE),
    // new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
    // new Ingredient("SLSA", "Salsa", Type.SAUCE),
    // new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

    Iterable<Ingredient> ingredients = ingredientRepo.findAll();
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
  }

  @PostMapping
  public String processTaco(@Valid Taco taco, Errors errors,
      @ModelAttribute TacoOrder tacoOrder) {
    if (errors.hasErrors()) {
      return "design";
    }
    tacoOrder.addTaco(taco);
    log.info("Processing Taco: {}", taco);
    return "redirect:/orders/current";
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  private Iterable<Ingredient> filterByType(
      Iterable<Ingredient> ingredients, Type type) {
    return StreamSupport.stream(ingredients.spliterator(), false)
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());

    // (ingredients)
    // .stream()
    // .filter(x -> x.getType().equals(type))
    // .collect(Collectors.toList());
  }
}
