package zuhriddinscode;

import zuhriddinscode.enums.ProfileRoleEnum;

public class ProfileDTO {

    private String id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private ProfileRoleEnum role;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ProfileRoleEnum getRole() {
        return role;
    }

    public void setRole(ProfileRoleEnum role) {
        this.role = role;
    }
}