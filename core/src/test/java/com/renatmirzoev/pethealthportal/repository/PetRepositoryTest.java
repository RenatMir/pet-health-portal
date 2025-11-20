package com.renatmirzoev.pethealthportal.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.renatmirzoev.pethealthportal.AbstractIntegrationTest;
import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.utils.PetTestUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

class PetRepositoryTest extends AbstractIntegrationTest {

  @Autowired private PetRepository petRepository;

  @Test
  void shouldSavePet() {
    Pet pet = PetTestUtils.createPet();
    Pet savedPet = petRepository.save(pet);

    assertThat(savedPet).isNotNull();
    assertThat(savedPet.getId()).isNotNull();
    assertThat(savedPet)
        .usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt", "version")
        .isEqualTo(pet);
  }

  @Test
  void shouldFindPetById() {
    Pet pet = PetTestUtils.createPet();
    Pet savedPet = petRepository.save(pet);

    Optional<Pet> foundPetOptional = petRepository.findById(savedPet.getId());

    assertThat(foundPetOptional).isPresent();
    Pet foundPet = foundPetOptional.get();

    assertThat(foundPet).usingRecursiveComparison().isEqualTo(savedPet);
  }

  @Test
  void shouldReturnEmptyWhenIdNotFound() {
    Optional<Pet> foundPet = petRepository.findById(999999L);
    assertThat(foundPet).isEmpty();
  }

  @Test
  void shouldFindPetByMicrochipId() {
    Pet pet = PetTestUtils.createPet();
    Pet savedPet = petRepository.save(pet);

    Optional<Pet> foundPetOptional = petRepository.findByMicrochipId(savedPet.getMicrochipId());

    assertThat(foundPetOptional).isPresent();
    Pet foundPet = foundPetOptional.get();

    assertThat(foundPet).usingRecursiveComparison().isEqualTo(savedPet);
  }

  @Test
  void shouldReturnEmptyWhenMicrochipIdNotFound() {
    Optional<Pet> foundPet = petRepository.findByMicrochipId("nonexistent");
    assertThat(foundPet).isEmpty();
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "   ", "\t", "\n"})
  void shouldSetDefaultNameWhenNameIsNullOrBlank(String name) {
    Pet pet = PetTestUtils.petBuilder().name(name).build();

    Pet savedPet = petRepository.save(pet);
    assertThat(savedPet.getName()).isEqualTo("Unnamed Pet");
  }
}
