package com.mostafa.student.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mostafa.student.domain.Student} entity.
 */
public class StudentDTO implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private Integer gander;

    private String studyGroup;

    private String nationalId;

    private String parentNumber;

    private String rate;

    private String address;

    private String remarks;

    private LocalDate joiningDate;

    private Integer price;

    private String currentSora;

    private String stage;

    private String grade;

    private String readAndWriteRate;

    private String lastTestRate;

    private String groupClass;

    private Boolean disConnected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGander() {
        return gander;
    }

    public void setGander(Integer gander) {
        this.gander = gander;
    }

    public String getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(String studyGroup) {
        this.studyGroup = studyGroup;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrentSora() {
        return currentSora;
    }

    public void setCurrentSora(String currentSora) {
        this.currentSora = currentSora;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getReadAndWriteRate() {
        return readAndWriteRate;
    }

    public void setReadAndWriteRate(String readAndWriteRate) {
        this.readAndWriteRate = readAndWriteRate;
    }

    public String getLastTestRate() {
        return lastTestRate;
    }

    public void setLastTestRate(String lastTestRate) {
        this.lastTestRate = lastTestRate;
    }

    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }

    public Boolean getDisConnected() {
        return disConnected;
    }

    public void setDisConnected(Boolean disConnected) {
        this.disConnected = disConnected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentDTO)) {
            return false;
        }

        StudentDTO studentDTO = (StudentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, studentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", age=" + getAge() +
            ", gander=" + getGander() +
            ", studyGroup='" + getStudyGroup() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", parentNumber='" + getParentNumber() + "'" +
            ", rate='" + getRate() + "'" +
            ", address='" + getAddress() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", joiningDate='" + getJoiningDate() + "'" +
            ", price=" + getPrice() +
            ", currentSora='" + getCurrentSora() + "'" +
            ", stage='" + getStage() + "'" +
            ", grade='" + getGrade() + "'" +
            ", readAndWriteRate='" + getReadAndWriteRate() + "'" +
            ", lastTestRate='" + getLastTestRate() + "'" +
            ", groupClass='" + getGroupClass() + "'" +
            ", disConnected='" + getDisConnected() + "'" +
            "}";
    }
}
