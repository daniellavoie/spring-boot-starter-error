package org.springframework.boot.error.repository;

import org.springframework.boot.error.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error, Long> {

}
