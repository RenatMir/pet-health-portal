package com.renatmirzoev.pethealthportal.controller;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatmirzoev.pethealthportal.mapper.PetMapper;
import com.renatmirzoev.pethealthportal.model.command.PatchPetCommand;
import com.renatmirzoev.pethealthportal.model.dto.PatchPetRequest;
import com.renatmirzoev.pethealthportal.model.dto.SavePetRequest;
import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.service.PetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {

  private final PetService petService;
  private final PetMapper petMapper;

  @GetMapping("/{id}")
  public Optional<Pet> getPet(@PathVariable long id) {
    return petService.getPetById(id);
  }

  @PostMapping
  public long savePet(@RequestBody @Valid final SavePetRequest request) {
    Pet pet = petMapper.toPet(request);
    return petService.savePet(pet);
  }

  @PatchMapping("/{id}")
  public void updatePet(@PathVariable long id, @RequestBody @Valid final PatchPetRequest request) {
    PatchPetCommand patchPetCommand = petMapper.toPatchPetCommand(id, request);
    petService.updatePet(patchPetCommand);
  }

}
