package com.example.hotelservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Contacts() {}

    public Contacts(Long id, String phone, String email) {
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String phone;
        private String email;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Contacts build() { return new Contacts(id, phone, email); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
