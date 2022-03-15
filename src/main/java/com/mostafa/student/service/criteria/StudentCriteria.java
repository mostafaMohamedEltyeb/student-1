package com.mostafa.student.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mostafa.student.domain.Student} entity. This class is used
 * in {@link com.mostafa.student.web.rest.StudentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /students?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StudentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private IntegerFilter age;

    private IntegerFilter gander;

    private StringFilter studyGroup;

    private StringFilter nationalId;

    private StringFilter parentNumber;

    private StringFilter rate;

    private StringFilter address;

    private StringFilter remarks;

    private LocalDateFilter joiningDate;

    private IntegerFilter price;

    public StudentCriteria() {}

    public StudentCriteria(StudentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.age = other.age == null ? null : other.age.copy();
        this.gander = other.gander == null ? null : other.gander.copy();
        this.studyGroup = other.studyGroup == null ? null : other.studyGroup.copy();
        this.nationalId = other.nationalId == null ? null : other.nationalId.copy();
        this.parentNumber = other.parentNumber == null ? null : other.parentNumber.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.joiningDate = other.joiningDate == null ? null : other.joiningDate.copy();
        this.price = other.price == null ? null : other.price.copy();
    }

    @Override
    public StudentCriteria copy() {
        return new StudentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public IntegerFilter getAge() {
        return age;
    }

    public IntegerFilter age() {
        if (age == null) {
            age = new IntegerFilter();
        }
        return age;
    }

    public void setAge(IntegerFilter age) {
        this.age = age;
    }

    public IntegerFilter getGander() {
        return gander;
    }

    public IntegerFilter gander() {
        if (gander == null) {
            gander = new IntegerFilter();
        }
        return gander;
    }

    public void setGander(IntegerFilter gander) {
        this.gander = gander;
    }

    public StringFilter getStudyGroup() {
        return studyGroup;
    }

    public StringFilter studyGroup() {
        if (studyGroup == null) {
            studyGroup = new StringFilter();
        }
        return studyGroup;
    }

    public void setStudyGroup(StringFilter studyGroup) {
        this.studyGroup = studyGroup;
    }

    public StringFilter getNationalId() {
        return nationalId;
    }

    public StringFilter nationalId() {
        if (nationalId == null) {
            nationalId = new StringFilter();
        }
        return nationalId;
    }

    public void setNationalId(StringFilter nationalId) {
        this.nationalId = nationalId;
    }

    public StringFilter getParentNumber() {
        return parentNumber;
    }

    public StringFilter parentNumber() {
        if (parentNumber == null) {
            parentNumber = new StringFilter();
        }
        return parentNumber;
    }

    public void setParentNumber(StringFilter parentNumber) {
        this.parentNumber = parentNumber;
    }

    public StringFilter getRate() {
        return rate;
    }

    public StringFilter rate() {
        if (rate == null) {
            rate = new StringFilter();
        }
        return rate;
    }

    public void setRate(StringFilter rate) {
        this.rate = rate;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public StringFilter remarks() {
        if (remarks == null) {
            remarks = new StringFilter();
        }
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    public LocalDateFilter getJoiningDate() {
        return joiningDate;
    }

    public LocalDateFilter joiningDate() {
        if (joiningDate == null) {
            joiningDate = new LocalDateFilter();
        }
        return joiningDate;
    }

    public void setJoiningDate(LocalDateFilter joiningDate) {
        this.joiningDate = joiningDate;
    }

    public IntegerFilter getPrice() {
        return price;
    }

    public IntegerFilter price() {
        if (price == null) {
            price = new IntegerFilter();
        }
        return price;
    }

    public void setPrice(IntegerFilter price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StudentCriteria that = (StudentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(age, that.age) &&
            Objects.equals(gander, that.gander) &&
            Objects.equals(studyGroup, that.studyGroup) &&
            Objects.equals(nationalId, that.nationalId) &&
            Objects.equals(parentNumber, that.parentNumber) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(address, that.address) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(joiningDate, that.joiningDate) &&
            Objects.equals(price, that.price)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gander, studyGroup, nationalId, parentNumber, rate, address, remarks, joiningDate, price);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StudentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (age != null ? "age=" + age + ", " : "") +
            (gander != null ? "gander=" + gander + ", " : "") +
            (studyGroup != null ? "studyGroup=" + studyGroup + ", " : "") +
            (nationalId != null ? "nationalId=" + nationalId + ", " : "") +
            (parentNumber != null ? "parentNumber=" + parentNumber + ", " : "") +
            (rate != null ? "rate=" + rate + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (joiningDate != null ? "joiningDate=" + joiningDate + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            "}";
    }
}
