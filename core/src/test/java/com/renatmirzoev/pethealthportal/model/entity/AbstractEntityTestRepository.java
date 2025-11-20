package com.renatmirzoev.pethealthportal.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractEntityTestRepository
    extends JpaRepository<AbstractEntityTestEntity, Long> {}
