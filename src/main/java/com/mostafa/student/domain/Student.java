package com.mostafa.student.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gander")
    private Integer gander;

    @Column(name = "study_group")
    private String studyGroup;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "parent_number")
    private String parentNumber;

    @Column(name = "rate")
    private String rate;

    @Column(name = "address")
    private String address;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "price")
    private Integer price;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public Student age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGander() {
        return this.gander;
    }

    public Student gander(Integer gander) {
        this.gander = gander;
        return this;
    }

    public void setGander(Integer gander) {
        this.gander = gander;
    }

    public String getStudyGroup() {
        return this.studyGroup;
    }

    public Student studyGroup(String studyGroup) {
        this.studyGroup = studyGroup;
        return this;
    }

    public void setStudyGroup(String studyGroup) {
        this.studyGroup = studyGroup;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public Student nationalId(String nationalId) {
        this.nationalId = nationalId;
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getParentNumber() {
        return this.parentNumber;
    }

    public Student parentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
        return this;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getRate() {
        return this.rate;
    }

    public Student rate(String rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAddress() {
        return this.address;
    }

    public Student address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Student remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getJoiningDate() {
        return this.joiningDate;
    }

    public Student joiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
        return this;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Student price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Student{" +
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
            "}";
    }
}
