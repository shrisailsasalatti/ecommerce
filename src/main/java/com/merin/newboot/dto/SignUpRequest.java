package com.merin.newboot.dto;

import com.merin.newboot.entity.User;

public class SignUpRequest {
   
        private String firstName;
        private String lastName;
        private String generatedUsername; // New field for the generated username
        private String password;
        private String email;
        private String phoneNumber;

        // Getters and setters for all fields

        public void generateUsername() {
            generatedUsername = firstName + " " + lastName; // You can modify the concatenation as needed
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public User toUser() {
            generateUsername(); // Call the method to generate the username
            return new User(null, firstName, lastName, generatedUsername, password, email, phoneNumber, null, null);
        }
    }

