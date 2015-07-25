package org.springframework.boot.error.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.error.model.Error;
import org.springframework.boot.error.repository.ErrorRepository;
import org.springframework.boot.error.service.ErrorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {
	@Autowired
	private ErrorRepository errorRepository;

	@Override
	public void delete(long id) {
		this.errorRepository.delete(id);
	}

	@Override
	public Page<Error> findAll(Pageable pageable) {
		return this.errorRepository.findAll(pageable);
	}

	@Override
	public Error findOne(long id) {
		return errorRepository.findOne(id);
	}

	@Override
	public Error save(Error error) {
		return errorRepository.saveAndFlush(error);
	}

}
