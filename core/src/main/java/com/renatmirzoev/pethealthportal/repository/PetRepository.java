package com.renatmirzoev.pethealthportal.repository;

import com.renatmirzoev.pethealthportal.model.entity.Pet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

  Optional<Pet> findByMicrochipId(String microchipId);
}
