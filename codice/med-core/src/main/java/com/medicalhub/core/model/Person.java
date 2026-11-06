package com.medicalhub.core.model;

import com.medicalhub.core.model.role.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Entità Person che agisce da 'Player' nel pattern Player-Role (Lethbridge 6.4).
 * Una persona ha un'identità (codice fiscale) e può assumere molteplici ruoli contemporaneamente.
 */
public class Person {
    private String taxCode;    // Codice Fiscale (chiave primaria)
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private final List<UserRole> roles;

    public Person(String taxCode, String firstName, String lastName, LocalDate birthDate) {
        if (taxCode == null || taxCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Il codice fiscale è obbligatorio");
        }
        this.taxCode = taxCode.trim().toUpperCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.roles = new ArrayList<>();
    }

    /**
     * Aggiunge un ruolo alla persona. Se la persona possiede già un ruolo della stessa classe, lo sostituisce.
     */
    public void addRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Il ruolo da aggiungere non può essere null");
        }
        roles.removeIf(r -> r.getClass().equals(role.getClass()));
        roles.add(role);
    }

    /**
     * Rimuove un ruolo specificando il tipo di classe.
     */
    public <T extends UserRole> boolean removeRole(Class<T> roleClass) {
        return roles.removeIf(r -> roleClass.isAssignableFrom(r.getClass()));
    }

    /**
     * Verifica se la persona possiede un determinato ruolo.
     */
    public <T extends UserRole> boolean hasRole(Class<T> roleClass) {
        return roles.stream().anyMatch(r -> roleClass.isAssignableFrom(r.getClass()));
    }

    /**
     * Restituisce l'istanza del ruolo specificato wrapped in un Optional.
     */
    @SuppressWarnings("unchecked")
    public <T extends UserRole> Optional<T> getRole(Class<T> roleClass) {
        return roles.stream()
                .filter(r -> roleClass.isAssignableFrom(r.getClass()))
                .map(r -> (T) r)
                .findFirst();
    }

    public List<UserRole> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + taxCode + ")";
    }
}
