package com.tacocloud.tacocloud.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tacocloud.tacocloud.model.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientReporsitory {

  private JdbcTemplate jdbcTemplate;

  // @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private Ingredient mapToRowIngredient(ResultSet row, int rowNum) throws SQLException {
    return new Ingredient(
        row.getString("id"),
        row.getString("name"),
        Ingredient.Type.valueOf(row.getString("type")));
  }

  @Override
  public Iterable<Ingredient> findAll() {
    return jdbcTemplate.query("select id, name, type from Ingredient", this::mapToRowIngredient);
  }

  @Override
  public Optional<Ingredient> findById(String id) {
    List<Ingredient> result = jdbcTemplate.query("select id, name, type from Ingredient where id = ?",
        this::mapToRowIngredient, id);
    return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbcTemplate.update("insert into Ingredient (id, name, type) values (? ,? ,?)",
        ingredient.getId(),
        ingredient.getName(),
        ingredient.getType().toString());

    return ingredient;
  }

}
