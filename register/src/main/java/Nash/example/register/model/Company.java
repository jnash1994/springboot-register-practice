package Nash.example.register.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
private String name;
private Integer size;
@JsonProperty("contact_phone")
private String contactPhone;

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
