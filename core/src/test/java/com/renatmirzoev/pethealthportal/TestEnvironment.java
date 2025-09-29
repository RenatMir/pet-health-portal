package com.renatmirzoev.pethealthportal;

import java.util.stream.Stream;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestEnvironment {

  private static final TestEnvironment INSTANCE = new TestEnvironment();

  private static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

  static {
    POSTGRES_CONTAINER = createPostgresContainer();

    Stream.of(POSTGRES_CONTAINER)
      .parallel()
      .forEach(GenericContainer::start);
  }

  private TestEnvironment() {
  }

  public static TestEnvironment getInstance() {
    return INSTANCE;
  }

  public String postgresUrl() {
    return String.format("jdbc:postgresql://%s:%s/core",
      POSTGRES_CONTAINER.getHost(),
      POSTGRES_CONTAINER.getFirstMappedPort().toString()
    );
  }

  @SuppressWarnings("resource")
  private static PostgreSQLContainer<?> createPostgresContainer() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17-alpine"))
      .withDatabaseName("core")
      .withUsername("core")
      .withPassword("core")
      .waitingFor(Wait.forListeningPort());
  }

}
