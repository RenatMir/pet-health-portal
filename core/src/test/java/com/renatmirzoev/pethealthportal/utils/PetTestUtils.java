package com.renatmirzoev.pethealthportal.utils;

import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.model.entity.Pet.Gender;
import com.renatmirzoev.pethealthportal.model.entity.Pet.Species;
import com.renatmirzoev.pethealthportal.model.rest.CreatePetRequest;
import java.time.LocalDate;

public class PetTestUtils {

  private PetTestUtils() {}

  public static Pet createPet() {
    return petBuilder().build();
  }

  public static Pet.PetBuilder petBuilder() {
    return Pet.builder()
        .name("Buddy")
        .species(Species.DOG)
        .gender(Gender.MALE)
        .birthDate(LocalDate.now().minusYears(3))
        .microchipId("123456789012345")
        .weightInGrams(15000);
  }

  public static CreatePetRequest createPetRequest() {
    Pet pet = createPet();
    return CreatePetRequest.builder()
        .species(pet.getSpecies())
        .gender(pet.getGender())
        .birthDate(pet.getBirthDate())
        .weightInGrams(pet.getWeightInGrams())
        .build();
  }

  public static CreatePetRequest.CreatePetRequestBuilder createPetRequestBuilder() {
    Pet pet = createPet();
    return CreatePetRequest.builder()
        .species(pet.getSpecies())
        .gender(pet.getGender())
        .birthDate(pet.getBirthDate())
        .weightInGrams(pet.getWeightInGrams());
  }
}
