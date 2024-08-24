package com.devmatheus.vendas.entities.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

public enum Role {
    ROLE_CUSTOMER(1),
    ROLE_ADMINISTRATOR(1);

    int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Role valueOf(int code){
        for (Role role : Role.values()) {
            if(role.getCode() == code){
                return role;
            }
        }
        throw new IllegalIdentifierException("Invalid role");
    }
}
