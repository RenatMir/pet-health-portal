package com.renatmirzoev.pethealthportal.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.renatmirzoev.pethealthportal.model.command.PatchPetCommand;
import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.repository.PetRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

  private final PetRepository petRepository;

  public Optional<Pet> getPetById(long id) {
    return petRepository.findById(id);
  }

  public long savePet(Pet pet) {
    return petRepository.save(pet);
  }

  public void updatePet(PatchPetCommand command) {
    Pet pet = petRepository.findById(command.getId()).orElseThrow(RuntimeException::new);

    if (StringUtils.hasText(command.getName())) {
      pet.setName(command.getName());
    }
    if (StringUtils.hasText(command.getBreed())) {
      pet.setBreed(command.getBreed());
    }
    if (command.getGender() != null) {
      pet.setGender(command.getGender());
    }
    if (command.getBirthDate() != null) {
      pet.setBirthDate(command.getBirthDate());
    }
    if (StringUtils.hasText(command.getMicrochipId())) {
      pet.setMicrochipId(command.getMicrochipId());
    }
    if (command.getWeightInGrams() != null) {
      pet.setWeightInGrams(command.getWeightInGrams());
    }

    petRepository.update(pet);
  }

}
