package com.renatmirzoev.pethealthportal.model.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.renatmirzoev.pethealthportal.model.PetGender;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatchPetCommand {

  private long id;
  private String name;
  private String breed;
  private PetGender gender;
  private LocalDate birthDate;
  private String microchipId;
  private BigDecimal weightInGrams;

}
