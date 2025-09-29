package com.renatmirzoev.pethealthportal.repository;

import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.renatmirzoev.pethealthportal.model.PetGender;
import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.utils.JdbcUtils;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PetRepository {

  private static final String SQL_SELECT_BY_ID = """
    SELECT * FROM pet WHERE id = ?;
    """;

  private static final String SQL_SAVE = """
    INSERT INTO pet (owner_id, name, breed, gender, birth_date, microchip_id, weight_in_grams)
    VALUES (:ownerId, :name, :breed, :gender, :birthDate, :microchipId, :weightInGrams)
    RETURNING id;
    """;

  private static final String SQL_UPDATE = """
    UPDATE pet SET name = :name,
                   breed = :breed,
                   gender = :gender,
                   birth_date = :birthDate,
                   microchip_id = :microchipId,
                   weight_in_grams = :weightInGrams
    WHERE id = :id;
    """;
  private static final RowMapper<Pet> ROW_MAPPER = (rs, _) -> (Pet) new Pet()
    .setId(rs.getLong("id"))
    .setOwnerId(rs.getObject("owner_id", Long.class))
    .setName(rs.getString("name"))
    .setBreed(rs.getString("breed"))
    .setGender(PetGender.valueOf(rs.getString("gender")))
    .setBirthDate(JdbcUtils.dateToLocalDate(rs.getDate("birth_date")))
    .setMicrochipId(rs.getString("microchip_id"))
    .setWeightInGrams(rs.getBigDecimal("weight_in_grams"))
    .setCreatedAt(JdbcUtils.timestampToInstant(rs.getTimestamp("created_at")))
    .setUpdatedAt(JdbcUtils.timestampToInstant(rs.getTimestamp("updated_at")));
  private final JdbcClient jdbcClient;

  public Optional<Pet> findById(long id) {
    return jdbcClient.sql(SQL_SELECT_BY_ID)
      .param(id)
      .query(ROW_MAPPER)
      .optional();
  }

  public long save(Pet pet) {
    return jdbcClient.sql(SQL_SAVE)
      .paramSource(toParams(pet))
      .query(Long.class)
      .single();
  }

  public int update(Pet pet) {
    return jdbcClient.sql(SQL_UPDATE)
      .paramSource(toParams(pet))
      .update();
  }

  private static MapSqlParameterSource toParams(Pet pet) {
    var params = new MapSqlParameterSource();
    params.addValue("id", pet.getId());
    params.addValue("ownerId", pet.getOwnerId());
    params.addValue("name", pet.getName());
    params.addValue("breed", pet.getBreed());
    params.addValue("gender", pet.getGender().name());
    params.addValue("birthDate", pet.getBirthDate());
    params.addValue("microchipId", pet.getMicrochipId());
    params.addValue("weightInGrams", pet.getWeightInGrams());
    params.addValue("createdAt", pet.getCreatedAt());
    params.addValue("updatedAt", pet.getUpdatedAt());
    return params;
  }

}
