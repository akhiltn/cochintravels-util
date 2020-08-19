package com.cochintravels.until.service;

import com.cochintravels.until.model.SheetJson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends CrudRepository<SheetJson, String> {
}