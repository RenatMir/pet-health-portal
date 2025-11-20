package com.renatmirzoev.pethealthportal.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.renatmirzoev.pethealthportal.AbstractIntegrationTest;
import com.renatmirzoev.pethealthportal.model.entity.Pet;
import com.renatmirzoev.pethealthportal.model.rest.CreatePetRequest;
import com.renatmirzoev.pethealthportal.repository.PetRepository;
import com.renatmirzoev.pethealthportal.utils.PetTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PetControllerTest extends AbstractIntegrationTest {

  @Autowired private PetRepository petRepository;

  @Test
  void shouldCreatePetAndFetchItById() {
    CreatePetRequest request = PetTestUtils.createPetRequest();

    ResponseEntity<Pet> createResponse =
        restTemplate.postForEntity("/api/pets", request, Pet.class);

    assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    String location = createResponse.getHeaders().getFirst(HttpHeaders.LOCATION);
    Pet createdPet = createResponse.getBody();
    assertThat(createdPet).isNotNull();
    assertThat(location).isNotNull().endsWith("/api/pets/" + createdPet.getId());

    ResponseEntity<Pet> getByIdResponse =
        restTemplate.getForEntity("/api/pets/" + createdPet.getId(), Pet.class);

    assertThat(getByIdResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Pet fetchedPet = getByIdResponse.getBody();
    assertThat(fetchedPet).isNotNull();
    assertThat(fetchedPet.getSpecies()).isEqualTo(request.species());
    assertThat(fetchedPet.getGender()).isEqualTo(request.gender());
    assertThat(fetchedPet.getWeightInGrams()).isEqualTo(request.weightInGrams());

    Pet petFromDb = petRepository.findById(createdPet.getId()).orElseThrow();
    assertThat(petFromDb.getSpecies()).isEqualTo(request.species());
    assertThat(petFromDb.getGender()).isEqualTo(request.gender());
    assertThat(petFromDb.getWeightInGrams()).isEqualTo(request.weightInGrams());
  }

  @Test
  void shouldReturnBadRequestWhenCreatePetWithInvalidData() {
    CreatePetRequest invalidRequest = CreatePetRequest.builder().build();

    ResponseEntity<String> response =
        restTemplate.postForEntity("/api/pets", invalidRequest, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void shouldReturnNotFoundWhenPetByIdDoesNotExist() {
    ResponseEntity<Pet> response = restTemplate.getForEntity("/api/pets/99999", Pet.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void shouldReturnNotFoundWhenPetByMicrochipIdDoesNotExist() {
    ResponseEntity<Pet> response =
        restTemplate.getForEntity("/api/pets/microchip/notfound", Pet.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
