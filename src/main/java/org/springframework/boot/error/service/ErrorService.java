package org.springframework.boot.error.service;

import org.springframework.boot.error.model.Error;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ErrorService {
	void delete(long id);

	Page<Error> findAll(Pageable pageable);

	Error findOne(long id);

	Error save(Error error);
}
