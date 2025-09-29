package com.renatmirzoev.pethealthportal.model.entity;

import java.time.Instant;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AbstractEntity {

  private Instant createdAt;
  private Instant updatedAt;

}
