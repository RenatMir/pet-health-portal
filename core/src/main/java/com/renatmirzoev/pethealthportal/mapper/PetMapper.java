package com.renatmirzoev.pethealthportal.mapper;

import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.model.rest.CreatePetRequest;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface PetMapper {

  Pet toPet(CreatePetRequest request);
}
