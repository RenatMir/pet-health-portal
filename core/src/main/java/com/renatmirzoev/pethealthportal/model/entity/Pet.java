package com.renatmirzoev.pethealthportal.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet")
@EqualsAndHashCode(callSuper = true)
public class Pet extends AbstractEntity {

  static final String UNNAMED_PET_DEFAULT_NAME = "Unnamed Pet";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "owner_id")
  private Long ownerId;

  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "species", nullable = false)
  @Enumerated(EnumType.STRING)
  private Species species;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private Gender gender;

  @NotNull
  @Past
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "microchip_id", unique = true)
  private String microchipId;

  @NotNull
  @Positive
  @Column(name = "weight_in_grams", nullable = false)
  private Integer weightInGrams;

  public Integer getAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

  @PrePersist
  @PreUpdate
  private void setDefaultName() {
    if (StringUtils.isBlank(name)) {
      this.name = UNNAMED_PET_DEFAULT_NAME;
    }
  }

  public enum Gender {
    MALE,
    FEMALE
  }

  public enum Species {
    DOG,
    CAT,
    OTHER
  }
}
