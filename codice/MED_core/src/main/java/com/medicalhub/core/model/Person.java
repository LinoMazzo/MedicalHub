package com.medicalhub.core.model;

import com.medicalhub.core.model.role.UserRole;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Person {
    
    private final String taxCode;    
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    
    private final Map<Class<? extends UserRole>, UserRole> roles;

    public Person(String taxCode, String firstName, String lastName, LocalDate birthDate) {
        if (taxCode == null || taxCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Il codice fiscale è obbligatorio");
        }
        this.taxCode = taxCode.trim().toUpperCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.roles = new HashMap<>();
    }

    public void addRole(UserRole role) {
        if (role != null) {
            roles.put(role.getClass(), role);
        }
    }

    /**
     * Rimuove un ruolo.
     */
    public void removeRole(Class<? extends UserRole> roleClass) {
        roles.remove(roleClass);
    }

    /**
     * Verifica se la persona ha un determinato ruolo in O(1).
     */
    public boolean hasRole(Class<? extends UserRole> roleClass) {
        return roles.containsKey(roleClass);
    }

    /**
     * Restituisce il ruolo specifico (con un semplice cast), oppure null se non esiste.
     */
    @SuppressWarnings("unchecked")
    public <T extends UserRole> T getRole(Class<T> roleClass) {
        return (T) roles.get(roleClass);
    }

    /**
     * Restituisce tutti i ruoli in sola lettura.
     */
    public Collection<UserRole> getRoles() {
        return Collections.unmodifiableCollection(roles.values());
    }

    // --- GETTER E SETTER ---

    public String getTaxCode() {
        return taxCode;
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