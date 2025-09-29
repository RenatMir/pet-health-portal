package com.renatmirzoev.pethealthportal.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.renatmirzoev.pethealthportal.model.PetGender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Pet extends AbstractEntity {

  private long id;
  private Long ownerId;
  private String name;
  private String breed;
  private PetGender gender;
  private LocalDate birthDate;
  private String microchipId;
  private BigDecimal weightInGrams;

}
