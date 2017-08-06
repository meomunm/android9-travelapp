package techkids.vn.travelapp.models;

import java.io.Serializable;

/**
 * Created by ADMIN on 8/6/2017.
 */

public class ProfileModel implements Serializable{
    public static final String KEY = "ProfileModel";
    private String id;
    private String name;
    private String mail;

    public ProfileModel() {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
