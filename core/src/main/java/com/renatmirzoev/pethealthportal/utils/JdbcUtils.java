package com.renatmirzoev.pethealthportal.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.lang.Nullable;

public class JdbcUtils {

  private JdbcUtils() {}

  public static Instant timestampToInstant(@Nullable Timestamp timestamp) {
    return Optional.ofNullable(timestamp).map(Timestamp::toInstant).orElse(null);
  }

  public static LocalDate dateToLocalDate(@Nullable Date date) {
    return Optional.ofNullable(date).map(Date::toLocalDate).orElse(null);
  }

}
