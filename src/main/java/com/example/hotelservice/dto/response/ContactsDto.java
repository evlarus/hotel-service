package com.example.hotelservice.dto.response;

public class ContactsDto {
    private String phone;
    private String email;

    public ContactsDto() {}

    public ContactsDto(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String phone;
        private String email;

        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public ContactsDto build() { return new ContactsDto(phone, email); }
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
