package com.renatmirzoev.pethealthportal.controller;

import com.renatmirzoev.pethealthportal.mapper.PetMapper;
import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.model.rest.CreatePetRequest;
import com.renatmirzoev.pethealthportal.service.PetService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

  private final PetMapper petMapper;
  private final PetService petService;

  @GetMapping("/{id}")
  // TODO: Replace Pet with PetResponse DTO
  public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
    return petService
        .getPetById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/microchip/{microchipId}")
  public ResponseEntity<Pet> getPetByMicrochipId(@PathVariable String microchipId) {
    return petService
        .getPetByMicrochipId(microchipId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Pet> createPet(@RequestBody @Valid CreatePetRequest createPetRequest) {
    Pet pet = petMapper.toPet(createPetRequest);
    pet = petService.createPet(pet);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(pet.getId())
            .toUri();
    return ResponseEntity.created(location).body(pet);
  }
}
