package com.renatmirzoev.pethealthportal.mapper;

import org.mapstruct.Mapper;

import com.renatmirzoev.pethealthportal.model.command.PatchPetCommand;
import com.renatmirzoev.pethealthportal.model.dto.PatchPetRequest;
import com.renatmirzoev.pethealthportal.model.dto.SavePetRequest;
import com.renatmirzoev.pethealthportal.model.entity.Pet;

@Mapper(config = MapperConfig.class)
public interface PetMapper {

  Pet toPet(SavePetRequest request);

  default PatchPetCommand toPatchPetCommand(Long id, PatchPetRequest request) {
    if (id == null) {
      return null;
    }

    return new PatchPetCommand()
      .setId(id)
      .setName(request.getName())
      .setBreed(request.getBreed())
      .setGender(request.getGender())
      .setBirthDate(request.getBirthDate())
      .setMicrochipId(request.getMicrochipId())
      .setWeightInGrams(request.getWeightInGrams());
  }

}
