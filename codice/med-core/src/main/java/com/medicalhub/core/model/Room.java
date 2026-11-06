package com.medicalhub.core.model;

/**
 * Rappresenta una stanza o ambulatorio di riabilitazione con attrezzature specifiche.
 */
public class Room {
    private String id;
    private String name;
    private String equipmentType;

    public Room(String id, String name, String equipmentType) {
        this.id = id;
        this.name = name;
        this.equipmentType = equipmentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    @Override
    public String toString() {
        return name + " [" + equipmentType + "]";
    }
}
