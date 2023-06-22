package com.solvd.db.mysql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;
@JsonRootName(value = "contact_information")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "contact_information")
public class ContactInformation {
    @XmlAttribute
    @JsonProperty("id")
    private long id;
    @XmlElement
    @JsonProperty("first_name")
    private String name;
    @XmlElement
    @JsonProperty("last_name")
    private String lastName;
    @XmlElement
    @JsonProperty("email")
    private String email;
    @XmlElement
    @JsonProperty("address")
    private String address;
    @XmlElement
    @JsonProperty("phone_number")
    private String phoneNumber;

    public ContactInformation(){}

    public ContactInformation( String name, String lastName, String email, String address,
                              String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public ContactInformation(long id, String name, String lastName, String email, String address,
                              String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
