package com.renatmirzoev.pethealthportal.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.renatmirzoev.pethealthportal.model.PetGender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SavePetRequest {

  private Long ownerId;
  @NotBlank
  private String name;
  private String breed;
  @NotNull
  private PetGender gender;
  @Past
  private LocalDate birthDate;
  private String microchipId;
  private BigDecimal weightInGrams;

}
