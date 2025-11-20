package com.renatmirzoev.pethealthportal.service;

import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.repository.PetRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

  private final PetRepository petRepository;

  public Optional<Pet> getPetById(long id) {
    return petRepository.findById(id);
  }

  public Optional<Pet> getPetByMicrochipId(String microchipId) {
    return petRepository.findByMicrochipId(microchipId);
  }

  @Transactional
  public Pet createPet(Pet pet) {
    return petRepository.save(pet);
  }
}
