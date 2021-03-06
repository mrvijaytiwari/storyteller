package com.adroitandroid.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by pv on 02/12/16.
 */
@Entity
@Table(name = "auth_type")
public class AuthenticationType {

    static final String FACEBOOK = "facebook";
    public static final String PHONE = "phone";
    static final String EMAIL = "email";
    public static final String GOOGLE = "google";

    @Id
    private Integer id;
    private String type;

    private AuthenticationType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public AuthenticationType() {
//        empty required by hibernate
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static AuthenticationType getByType(String type) {
        switch (type) {
            case FACEBOOK:
                return new AuthenticationType(1, FACEBOOK);
            case PHONE:
                return new AuthenticationType(2, PHONE);
            case GOOGLE:
                return new AuthenticationType(3, GOOGLE);
            case EMAIL:
                return new AuthenticationType(4, EMAIL);
        }
        throw new IllegalArgumentException("invalid authentication type");
    }
}
