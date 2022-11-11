package com.revature.model;

import java.util.Date;
import java.util.Objects;

public class Order {
    private String filterid;
    private String skillset;
    private String location;
    private String availability;
    private String salary;
    private String experience;
    private String education;
    private String certifications;
    private String languages;
    private String frameworks;
    private String databases;
    private String operatingsystems;
    private String tools;
    private String hobbies;

    public Order() {
    }

    public Order(String filterid, String skillset, String location, String availability, String salary, String experience, String education, String certifications, String languages, String frameworks, String databases, String operatingsystems, String tools, String hobbies) {
        this.filterid = filterid;
        this.skillset = skillset;
        this.location = location;
        this.availability = availability;
        this.salary = salary;
        this.experience = experience;
        this.education = education;
        this.certifications = certifications;
        this.languages = languages;
        this.frameworks = frameworks;
        this.databases = databases;
        this.operatingsystems = operatingsystems;
        this.tools = tools;
        this.hobbies = hobbies;
    }

    public String getFilterID() {
        return filterid;
    }

    public void setFilterID(String filterID) {
        this.filterid = filterID;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(String frameworks) {
        this.frameworks = frameworks;
    }

    public String getDatabases() {
        return databases;
    }

    public void setDatabases(String databases) {
        this.databases = databases;
    }

    public String getOperatingsystems() {
        return operatingsystems;
    }

    public void setOperatingsystems(String operatingsystems) {
        this.operatingsystems = operatingsystems;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Order{" +
                "filterID=" + filterid +
                ", skillset='" + skillset + '\'' +
                ", location='" + location + '\'' +
                ", availability='" + availability + '\'' +
                ", salary='" + salary + '\'' +
                ", experience='" + experience + '\'' +
                ", education='" + education + '\'' +
                ", certifications='" + certifications + '\'' +
                ", languages='" + languages + '\'' +
                ", frameworks='" + frameworks + '\'' +
                ", databases='" + databases + '\'' +
                ", operatingsystems='" + operatingsystems + '\'' +
                ", tools='" + tools + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return filterid == order.filterid && Objects.equals(skillset, order.skillset) && Objects.equals(location, order.location) && Objects.equals(availability, order.availability) && Objects.equals(salary, order.salary) && Objects.equals(experience, order.experience) && Objects.equals(education, order.education) && Objects.equals(certifications, order.certifications) && Objects.equals(languages, order.languages) && Objects.equals(frameworks, order.frameworks) && Objects.equals(databases, order.databases) && Objects.equals(operatingsystems, order.operatingsystems) && Objects.equals(tools, order.tools) && Objects.equals(hobbies, order.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterid, skillset, location, availability, salary, experience, education, certifications, languages, frameworks, databases, operatingsystems, tools, hobbies);
    }
}
