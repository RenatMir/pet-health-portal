package com.renatmirzoev.pethealthportal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.repository.PetRepository;
import com.renatmirzoev.pethealthportal.utils.PetTestUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

  @Mock private PetRepository petRepository;

  @InjectMocks private PetService petService;

  @Test
  void shouldGetPetByIdWhenExists() {
    Pet pet = PetTestUtils.petBuilder().id(1L).build();
    when(petRepository.findById(pet.getId())).thenReturn(Optional.of(pet));

    Optional<Pet> result = petService.getPetById(pet.getId());

    assertThat(result).isPresent();
    assertThat(result.get()).usingRecursiveComparison().isEqualTo(pet);
    verify(petRepository).findById(pet.getId());
  }

  @Test
  void shouldReturnEmptyWhenPetByIdNotFound() {
    Long nonExistentId = 999L;
    when(petRepository.findById(nonExistentId)).thenReturn(Optional.empty());

    Optional<Pet> result = petService.getPetById(nonExistentId);

    assertThat(result).isEmpty();
    verify(petRepository).findById(nonExistentId);
  }

  @Test
  void shouldGetPetByMicrochipIdWhenExists() {
    Pet pet = PetTestUtils.createPet();
    when(petRepository.findByMicrochipId(pet.getMicrochipId())).thenReturn(Optional.of(pet));

    Optional<Pet> result = petService.getPetByMicrochipId(pet.getMicrochipId());

    assertThat(result).isPresent();
    assertThat(result.get()).usingRecursiveComparison().isEqualTo(pet);
    verify(petRepository).findByMicrochipId(pet.getMicrochipId());
  }

  @Test
  void shouldReturnEmptyWhenPetByMicrochipIdNotFound() {
    String nonExistentMicrochipId = "nonexistent";
    when(petRepository.findByMicrochipId(nonExistentMicrochipId)).thenReturn(Optional.empty());

    Optional<Pet> result = petService.getPetByMicrochipId(nonExistentMicrochipId);

    assertThat(result).isEmpty();
    verify(petRepository).findByMicrochipId(nonExistentMicrochipId);
  }

  @Test
  void shouldCreatePet() {
    Pet inputPet = PetTestUtils.createPet();
    Pet savedPet = PetTestUtils.petBuilder().id(1L).build();

    when(petRepository.save(inputPet)).thenReturn(savedPet);

    Pet result = petService.createPet(inputPet);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(savedPet.getId());
    assertThat(result).usingRecursiveComparison().isEqualTo(savedPet);
    verify(petRepository).save(inputPet);
  }
}
