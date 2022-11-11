package com.revature.model;

import java.util.Objects;

public class Developer {
    private String firstName;
    private String lastName;
    private String location;
    private String availability;
    private int salary;
    private String education;
    private String certifications;
    private String experience;
    private String skillset;
    private String languages;
    private String tools;
    private String hobbies;
    private String resume;

    public Developer() {
    }

    public Developer(String firstName, String lastName, String location, String availability, int salary, String education, String certifications, String experience, String skillset, String languages, String tools, String hobbies, String resume) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.availability = availability;
        this.salary = salary;
        this.education = education;
        this.certifications = certifications;
        this.experience = experience;
        this.skillset = skillset;
        this.languages = languages;
        this.tools = tools;
        this.hobbies = hobbies;
        this.resume = resume;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", availability='" + availability + '\'' +
                ", salary='" + salary + '\'' +
                ", education='" + education + '\'' +
                ", certifications='" + certifications + '\'' +
                ", experience='" + experience + '\'' +
                ", skillset='" + skillset + '\'' +
                ", languages='" + languages + '\'' +
                ", tools='" + tools + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(firstName, developer.firstName) && Objects.equals(lastName, developer.lastName) && Objects.equals(location, developer.location) && Objects.equals(availability, developer.availability) && Objects.equals(salary, developer.salary) && Objects.equals(education, developer.education) && Objects.equals(certifications, developer.certifications) && Objects.equals(experience, developer.experience) && Objects.equals(skillset, developer.skillset) && Objects.equals(languages, developer.languages) && Objects.equals(tools, developer.tools) && Objects.equals(hobbies, developer.hobbies) && Objects.equals(resume, developer.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, location, availability, salary, education, certifications, experience, skillset, languages, tools, hobbies, resume);
    }
}
