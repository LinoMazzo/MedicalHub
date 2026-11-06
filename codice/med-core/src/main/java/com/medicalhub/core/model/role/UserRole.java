package com.medicalhub.core.model.role;

/**
 * Interfaccia radice per i ruoli associati ad una Persona nel pattern Player-Role.
 */
public interface UserRole {
    /**
     * Restituisce il nome descrittivo del ruolo.
     * @return Nome del ruolo
     */
    String getRoleName();
}
