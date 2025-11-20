package com.renatmirzoev.pethealthportal.model.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.renatmirzoev.pethealthportal.AbstractIntegrationTest;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class AbstractEntityAuditTest extends AbstractIntegrationTest {

  @Autowired private AbstractEntityTestRepository testRepository;

  @Test
  void shouldSetAuditFieldsOnInsert() {
    AbstractEntityTestEntity entity = new AbstractEntityTestEntity();
    entity.setName("test");

    AbstractEntityTestEntity saved = testRepository.saveAndFlush(entity);

    assertThat(saved.getVersion()).isZero();
    assertThat(saved.getCreatedAt()).isNotNull();
    assertThat(saved.getUpdatedAt()).isNotNull();
  }

  @Test
  void shouldUpdateAuditFieldsOnUpdate() {
    AbstractEntityTestEntity entity = new AbstractEntityTestEntity();
    entity.setName("test");
    AbstractEntityTestEntity saved = testRepository.saveAndFlush(entity);

    long initialVersion = saved.getVersion();
    Instant initialCreatedAt = saved.getCreatedAt();
    Instant initialUpdatedAt = saved.getUpdatedAt();

    saved.setName("test-updated");
    AbstractEntityTestEntity updated = testRepository.saveAndFlush(saved);

    assertThat(updated.getVersion()).isEqualTo(initialVersion + 1);
    assertThat(updated.getCreatedAt()).isEqualTo(initialCreatedAt);
    assertThat(updated.getUpdatedAt()).isAfter(initialUpdatedAt);
  }
}
