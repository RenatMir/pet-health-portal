package com.renatmirzoev.pethealthportal.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renatmirzoev.pethealthportal.AbstractIntegrationTest;
import com.renatmirzoev.pethealthportal.model.PetGender;
import com.renatmirzoev.pethealthportal.model.entity.Pet;

class PetRepositoryTest extends AbstractIntegrationTest {

  @Autowired
  private PetRepository petRepository;

  @Test
  void shouldSavePet() {
    Pet pet = new Pet().setName("Test").setGender(PetGender.MALE);

    long id = petRepository.save(pet);
    assertThat(id).isEqualTo(1);
  }

}