package com.medicalhub.core.repository;

import com.medicalhub.core.model.Person;
import java.util.List;
import java.util.Optional;

/**
 * Interfaccia di persistenza per le entità Person e relativi ruoli.
 */
public interface PersonDatabase {
    void save(Person person);
    Optional<Person> findByTaxCode(String taxCode);
    List<Person> findAll();
    List<Person> findByRoleClass(Class<?> roleClass);
    void deleteByTaxCode(String taxCode);
}
