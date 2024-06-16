package com.RobinCompany.BatchJava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String reimbursementId;
    private String socialSecurityNumber;
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String careCode;
    private String reimbursementAmount;
    private String fileTimestamp;

    public String getReimbursementId() { return reimbursementId; }
    public void setReimbursementId(String reimbursementId) { this.reimbursementId = reimbursementId; }

    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public void setSocialSecurityNumber(String socialSecurityNumber) { this.socialSecurityNumber = socialSecurityNumber; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCareCode() { return careCode; }
    public void setCareCode(String careCode) { this.careCode = careCode; }

    public String getReimbursementAmount() { return reimbursementAmount; }
    public void setReimbursementAmount(String reimbursementAmount) { this.reimbursementAmount = reimbursementAmount; }

    public String getFileTimestamp() { return fileTimestamp; }
    public void setFileTimestamp(String fileTimestamp) { this.fileTimestamp = fileTimestamp; }
}
