package com.renatmirzoev.pethealthportal.model.rest;

import com.renatmirzoev.pethealthportal.model.entity.Pet;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record CreatePetRequest(
    @Nullable Long ownerId,
    @Nullable String name,
    @NotNull Pet.Species species,
    @NotNull Pet.Gender gender,
    @NotNull @Past LocalDate birthDate,
    @Nullable String microchipId,
    @NotNull @Positive Integer weightInGrams) {}
