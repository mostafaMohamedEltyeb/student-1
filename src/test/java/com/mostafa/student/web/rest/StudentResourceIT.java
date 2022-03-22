package com.mostafa.student.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mostafa.student.IntegrationTest;
import com.mostafa.student.domain.Student;
import com.mostafa.student.repository.StudentRepository;
import com.mostafa.student.service.criteria.StudentCriteria;
import com.mostafa.student.service.dto.StudentDTO;
import com.mostafa.student.service.mapper.StudentMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StudentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StudentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;
    private static final Integer SMALLER_AGE = 1 - 1;

    private static final Integer DEFAULT_GANDER = 1;
    private static final Integer UPDATED_GANDER = 2;
    private static final Integer SMALLER_GANDER = 1 - 1;

    private static final String DEFAULT_STUDY_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_STUDY_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RATE = "AAAAAAAAAA";
    private static final String UPDATED_RATE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_JOINING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOINING_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_JOINING_DATE = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_PRICE = 1;
    private static final Integer UPDATED_PRICE = 2;
    private static final Integer SMALLER_PRICE = 1 - 1;

    private static final String DEFAULT_CURRENT_SORA = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_SORA = "BBBBBBBBBB";

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_READ_AND_WRITE_RATE = "AAAAAAAAAA";
    private static final String UPDATED_READ_AND_WRITE_RATE = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_TEST_RATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_TEST_RATE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_CLASS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DIS_CONNECTED = false;
    private static final Boolean UPDATED_DIS_CONNECTED = true;

    private static final String ENTITY_API_URL = "/api/students";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentMockMvc;

    private Student student;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .name(DEFAULT_NAME)
            .age(DEFAULT_AGE)
            .gander(DEFAULT_GANDER)
            .studyGroup(DEFAULT_STUDY_GROUP)
            .nationalId(DEFAULT_NATIONAL_ID)
            .parentNumber(DEFAULT_PARENT_NUMBER)
            .rate(DEFAULT_RATE)
            .address(DEFAULT_ADDRESS)
            .remarks(DEFAULT_REMARKS)
            .joiningDate(DEFAULT_JOINING_DATE)
            .price(DEFAULT_PRICE)
            .currentSora(DEFAULT_CURRENT_SORA)
            .stage(DEFAULT_STAGE)
            .grade(DEFAULT_GRADE)
            .readAndWriteRate(DEFAULT_READ_AND_WRITE_RATE)
            .lastTestRate(DEFAULT_LAST_TEST_RATE)
            .groupClass(DEFAULT_GROUP_CLASS)
            .disConnected(DEFAULT_DIS_CONNECTED);
        return student;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createUpdatedEntity(EntityManager em) {
        Student student = new Student()
            .name(UPDATED_NAME)
            .age(UPDATED_AGE)
            .gander(UPDATED_GANDER)
            .studyGroup(UPDATED_STUDY_GROUP)
            .nationalId(UPDATED_NATIONAL_ID)
            .parentNumber(UPDATED_PARENT_NUMBER)
            .rate(UPDATED_RATE)
            .address(UPDATED_ADDRESS)
            .remarks(UPDATED_REMARKS)
            .joiningDate(UPDATED_JOINING_DATE)
            .price(UPDATED_PRICE)
            .currentSora(UPDATED_CURRENT_SORA)
            .stage(UPDATED_STAGE)
            .grade(UPDATED_GRADE)
            .readAndWriteRate(UPDATED_READ_AND_WRITE_RATE)
            .lastTestRate(UPDATED_LAST_TEST_RATE)
            .groupClass(UPDATED_GROUP_CLASS)
            .disConnected(UPDATED_DIS_CONNECTED);
        return student;
    }

    @BeforeEach
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudent.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testStudent.getGander()).isEqualTo(DEFAULT_GANDER);
        assertThat(testStudent.getStudyGroup()).isEqualTo(DEFAULT_STUDY_GROUP);
        assertThat(testStudent.getNationalId()).isEqualTo(DEFAULT_NATIONAL_ID);
        assertThat(testStudent.getParentNumber()).isEqualTo(DEFAULT_PARENT_NUMBER);
        assertThat(testStudent.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testStudent.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStudent.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testStudent.getJoiningDate()).isEqualTo(DEFAULT_JOINING_DATE);
        assertThat(testStudent.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testStudent.getCurrentSora()).isEqualTo(DEFAULT_CURRENT_SORA);
        assertThat(testStudent.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testStudent.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testStudent.getReadAndWriteRate()).isEqualTo(DEFAULT_READ_AND_WRITE_RATE);
        assertThat(testStudent.getLastTestRate()).isEqualTo(DEFAULT_LAST_TEST_RATE);
        assertThat(testStudent.getGroupClass()).isEqualTo(DEFAULT_GROUP_CLASS);
        assertThat(testStudent.getDisConnected()).isEqualTo(DEFAULT_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void createStudentWithExistingId() throws Exception {
        // Create the Student with an existing ID
        student.setId(1L);
        StudentDTO studentDTO = studentMapper.toDto(student);

        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gander").value(hasItem(DEFAULT_GANDER)))
            .andExpect(jsonPath("$.[*].studyGroup").value(hasItem(DEFAULT_STUDY_GROUP)))
            .andExpect(jsonPath("$.[*].nationalId").value(hasItem(DEFAULT_NATIONAL_ID)))
            .andExpect(jsonPath("$.[*].parentNumber").value(hasItem(DEFAULT_PARENT_NUMBER)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].currentSora").value(hasItem(DEFAULT_CURRENT_SORA)))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].readAndWriteRate").value(hasItem(DEFAULT_READ_AND_WRITE_RATE)))
            .andExpect(jsonPath("$.[*].lastTestRate").value(hasItem(DEFAULT_LAST_TEST_RATE)))
            .andExpect(jsonPath("$.[*].groupClass").value(hasItem(DEFAULT_GROUP_CLASS)))
            .andExpect(jsonPath("$.[*].disConnected").value(hasItem(DEFAULT_DIS_CONNECTED.booleanValue())));
    }

    @Test
    @Transactional
    void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_ID, student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.gander").value(DEFAULT_GANDER))
            .andExpect(jsonPath("$.studyGroup").value(DEFAULT_STUDY_GROUP))
            .andExpect(jsonPath("$.nationalId").value(DEFAULT_NATIONAL_ID))
            .andExpect(jsonPath("$.parentNumber").value(DEFAULT_PARENT_NUMBER))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.joiningDate").value(DEFAULT_JOINING_DATE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.currentSora").value(DEFAULT_CURRENT_SORA))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.readAndWriteRate").value(DEFAULT_READ_AND_WRITE_RATE))
            .andExpect(jsonPath("$.lastTestRate").value(DEFAULT_LAST_TEST_RATE))
            .andExpect(jsonPath("$.groupClass").value(DEFAULT_GROUP_CLASS))
            .andExpect(jsonPath("$.disConnected").value(DEFAULT_DIS_CONNECTED.booleanValue()));
    }

    @Test
    @Transactional
    void getStudentsByIdFiltering() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        Long id = student.getId();

        defaultStudentShouldBeFound("id.equals=" + id);
        defaultStudentShouldNotBeFound("id.notEquals=" + id);

        defaultStudentShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultStudentShouldNotBeFound("id.greaterThan=" + id);

        defaultStudentShouldBeFound("id.lessThanOrEqual=" + id);
        defaultStudentShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllStudentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where name equals to DEFAULT_NAME
        defaultStudentShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the studentList where name equals to UPDATED_NAME
        defaultStudentShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllStudentsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where name not equals to DEFAULT_NAME
        defaultStudentShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the studentList where name not equals to UPDATED_NAME
        defaultStudentShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllStudentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where name in DEFAULT_NAME or UPDATED_NAME
        defaultStudentShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the studentList where name equals to UPDATED_NAME
        defaultStudentShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllStudentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where name is not null
        defaultStudentShouldBeFound("name.specified=true");

        // Get all the studentList where name is null
        defaultStudentShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByNameContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where name contains DEFAULT_NAME
        defaultStudentShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the studentList where name contains UPDATED_NAME
        defaultStudentShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllStudentsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where name does not contain DEFAULT_NAME
        defaultStudentShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the studentList where name does not contain UPDATED_NAME
        defaultStudentShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age equals to DEFAULT_AGE
        defaultStudentShouldBeFound("age.equals=" + DEFAULT_AGE);

        // Get all the studentList where age equals to UPDATED_AGE
        defaultStudentShouldNotBeFound("age.equals=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age not equals to DEFAULT_AGE
        defaultStudentShouldNotBeFound("age.notEquals=" + DEFAULT_AGE);

        // Get all the studentList where age not equals to UPDATED_AGE
        defaultStudentShouldBeFound("age.notEquals=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age in DEFAULT_AGE or UPDATED_AGE
        defaultStudentShouldBeFound("age.in=" + DEFAULT_AGE + "," + UPDATED_AGE);

        // Get all the studentList where age equals to UPDATED_AGE
        defaultStudentShouldNotBeFound("age.in=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age is not null
        defaultStudentShouldBeFound("age.specified=true");

        // Get all the studentList where age is null
        defaultStudentShouldNotBeFound("age.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age is greater than or equal to DEFAULT_AGE
        defaultStudentShouldBeFound("age.greaterThanOrEqual=" + DEFAULT_AGE);

        // Get all the studentList where age is greater than or equal to UPDATED_AGE
        defaultStudentShouldNotBeFound("age.greaterThanOrEqual=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age is less than or equal to DEFAULT_AGE
        defaultStudentShouldBeFound("age.lessThanOrEqual=" + DEFAULT_AGE);

        // Get all the studentList where age is less than or equal to SMALLER_AGE
        defaultStudentShouldNotBeFound("age.lessThanOrEqual=" + SMALLER_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsLessThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age is less than DEFAULT_AGE
        defaultStudentShouldNotBeFound("age.lessThan=" + DEFAULT_AGE);

        // Get all the studentList where age is less than UPDATED_AGE
        defaultStudentShouldBeFound("age.lessThan=" + UPDATED_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByAgeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where age is greater than DEFAULT_AGE
        defaultStudentShouldNotBeFound("age.greaterThan=" + DEFAULT_AGE);

        // Get all the studentList where age is greater than SMALLER_AGE
        defaultStudentShouldBeFound("age.greaterThan=" + SMALLER_AGE);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander equals to DEFAULT_GANDER
        defaultStudentShouldBeFound("gander.equals=" + DEFAULT_GANDER);

        // Get all the studentList where gander equals to UPDATED_GANDER
        defaultStudentShouldNotBeFound("gander.equals=" + UPDATED_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander not equals to DEFAULT_GANDER
        defaultStudentShouldNotBeFound("gander.notEquals=" + DEFAULT_GANDER);

        // Get all the studentList where gander not equals to UPDATED_GANDER
        defaultStudentShouldBeFound("gander.notEquals=" + UPDATED_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander in DEFAULT_GANDER or UPDATED_GANDER
        defaultStudentShouldBeFound("gander.in=" + DEFAULT_GANDER + "," + UPDATED_GANDER);

        // Get all the studentList where gander equals to UPDATED_GANDER
        defaultStudentShouldNotBeFound("gander.in=" + UPDATED_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander is not null
        defaultStudentShouldBeFound("gander.specified=true");

        // Get all the studentList where gander is null
        defaultStudentShouldNotBeFound("gander.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander is greater than or equal to DEFAULT_GANDER
        defaultStudentShouldBeFound("gander.greaterThanOrEqual=" + DEFAULT_GANDER);

        // Get all the studentList where gander is greater than or equal to UPDATED_GANDER
        defaultStudentShouldNotBeFound("gander.greaterThanOrEqual=" + UPDATED_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander is less than or equal to DEFAULT_GANDER
        defaultStudentShouldBeFound("gander.lessThanOrEqual=" + DEFAULT_GANDER);

        // Get all the studentList where gander is less than or equal to SMALLER_GANDER
        defaultStudentShouldNotBeFound("gander.lessThanOrEqual=" + SMALLER_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsLessThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander is less than DEFAULT_GANDER
        defaultStudentShouldNotBeFound("gander.lessThan=" + DEFAULT_GANDER);

        // Get all the studentList where gander is less than UPDATED_GANDER
        defaultStudentShouldBeFound("gander.lessThan=" + UPDATED_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByGanderIsGreaterThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where gander is greater than DEFAULT_GANDER
        defaultStudentShouldNotBeFound("gander.greaterThan=" + DEFAULT_GANDER);

        // Get all the studentList where gander is greater than SMALLER_GANDER
        defaultStudentShouldBeFound("gander.greaterThan=" + SMALLER_GANDER);
    }

    @Test
    @Transactional
    void getAllStudentsByStudyGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where studyGroup equals to DEFAULT_STUDY_GROUP
        defaultStudentShouldBeFound("studyGroup.equals=" + DEFAULT_STUDY_GROUP);

        // Get all the studentList where studyGroup equals to UPDATED_STUDY_GROUP
        defaultStudentShouldNotBeFound("studyGroup.equals=" + UPDATED_STUDY_GROUP);
    }

    @Test
    @Transactional
    void getAllStudentsByStudyGroupIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where studyGroup not equals to DEFAULT_STUDY_GROUP
        defaultStudentShouldNotBeFound("studyGroup.notEquals=" + DEFAULT_STUDY_GROUP);

        // Get all the studentList where studyGroup not equals to UPDATED_STUDY_GROUP
        defaultStudentShouldBeFound("studyGroup.notEquals=" + UPDATED_STUDY_GROUP);
    }

    @Test
    @Transactional
    void getAllStudentsByStudyGroupIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where studyGroup in DEFAULT_STUDY_GROUP or UPDATED_STUDY_GROUP
        defaultStudentShouldBeFound("studyGroup.in=" + DEFAULT_STUDY_GROUP + "," + UPDATED_STUDY_GROUP);

        // Get all the studentList where studyGroup equals to UPDATED_STUDY_GROUP
        defaultStudentShouldNotBeFound("studyGroup.in=" + UPDATED_STUDY_GROUP);
    }

    @Test
    @Transactional
    void getAllStudentsByStudyGroupIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where studyGroup is not null
        defaultStudentShouldBeFound("studyGroup.specified=true");

        // Get all the studentList where studyGroup is null
        defaultStudentShouldNotBeFound("studyGroup.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByStudyGroupContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where studyGroup contains DEFAULT_STUDY_GROUP
        defaultStudentShouldBeFound("studyGroup.contains=" + DEFAULT_STUDY_GROUP);

        // Get all the studentList where studyGroup contains UPDATED_STUDY_GROUP
        defaultStudentShouldNotBeFound("studyGroup.contains=" + UPDATED_STUDY_GROUP);
    }

    @Test
    @Transactional
    void getAllStudentsByStudyGroupNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where studyGroup does not contain DEFAULT_STUDY_GROUP
        defaultStudentShouldNotBeFound("studyGroup.doesNotContain=" + DEFAULT_STUDY_GROUP);

        // Get all the studentList where studyGroup does not contain UPDATED_STUDY_GROUP
        defaultStudentShouldBeFound("studyGroup.doesNotContain=" + UPDATED_STUDY_GROUP);
    }

    @Test
    @Transactional
    void getAllStudentsByNationalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where nationalId equals to DEFAULT_NATIONAL_ID
        defaultStudentShouldBeFound("nationalId.equals=" + DEFAULT_NATIONAL_ID);

        // Get all the studentList where nationalId equals to UPDATED_NATIONAL_ID
        defaultStudentShouldNotBeFound("nationalId.equals=" + UPDATED_NATIONAL_ID);
    }

    @Test
    @Transactional
    void getAllStudentsByNationalIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where nationalId not equals to DEFAULT_NATIONAL_ID
        defaultStudentShouldNotBeFound("nationalId.notEquals=" + DEFAULT_NATIONAL_ID);

        // Get all the studentList where nationalId not equals to UPDATED_NATIONAL_ID
        defaultStudentShouldBeFound("nationalId.notEquals=" + UPDATED_NATIONAL_ID);
    }

    @Test
    @Transactional
    void getAllStudentsByNationalIdIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where nationalId in DEFAULT_NATIONAL_ID or UPDATED_NATIONAL_ID
        defaultStudentShouldBeFound("nationalId.in=" + DEFAULT_NATIONAL_ID + "," + UPDATED_NATIONAL_ID);

        // Get all the studentList where nationalId equals to UPDATED_NATIONAL_ID
        defaultStudentShouldNotBeFound("nationalId.in=" + UPDATED_NATIONAL_ID);
    }

    @Test
    @Transactional
    void getAllStudentsByNationalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where nationalId is not null
        defaultStudentShouldBeFound("nationalId.specified=true");

        // Get all the studentList where nationalId is null
        defaultStudentShouldNotBeFound("nationalId.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByNationalIdContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where nationalId contains DEFAULT_NATIONAL_ID
        defaultStudentShouldBeFound("nationalId.contains=" + DEFAULT_NATIONAL_ID);

        // Get all the studentList where nationalId contains UPDATED_NATIONAL_ID
        defaultStudentShouldNotBeFound("nationalId.contains=" + UPDATED_NATIONAL_ID);
    }

    @Test
    @Transactional
    void getAllStudentsByNationalIdNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where nationalId does not contain DEFAULT_NATIONAL_ID
        defaultStudentShouldNotBeFound("nationalId.doesNotContain=" + DEFAULT_NATIONAL_ID);

        // Get all the studentList where nationalId does not contain UPDATED_NATIONAL_ID
        defaultStudentShouldBeFound("nationalId.doesNotContain=" + UPDATED_NATIONAL_ID);
    }

    @Test
    @Transactional
    void getAllStudentsByParentNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where parentNumber equals to DEFAULT_PARENT_NUMBER
        defaultStudentShouldBeFound("parentNumber.equals=" + DEFAULT_PARENT_NUMBER);

        // Get all the studentList where parentNumber equals to UPDATED_PARENT_NUMBER
        defaultStudentShouldNotBeFound("parentNumber.equals=" + UPDATED_PARENT_NUMBER);
    }

    @Test
    @Transactional
    void getAllStudentsByParentNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where parentNumber not equals to DEFAULT_PARENT_NUMBER
        defaultStudentShouldNotBeFound("parentNumber.notEquals=" + DEFAULT_PARENT_NUMBER);

        // Get all the studentList where parentNumber not equals to UPDATED_PARENT_NUMBER
        defaultStudentShouldBeFound("parentNumber.notEquals=" + UPDATED_PARENT_NUMBER);
    }

    @Test
    @Transactional
    void getAllStudentsByParentNumberIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where parentNumber in DEFAULT_PARENT_NUMBER or UPDATED_PARENT_NUMBER
        defaultStudentShouldBeFound("parentNumber.in=" + DEFAULT_PARENT_NUMBER + "," + UPDATED_PARENT_NUMBER);

        // Get all the studentList where parentNumber equals to UPDATED_PARENT_NUMBER
        defaultStudentShouldNotBeFound("parentNumber.in=" + UPDATED_PARENT_NUMBER);
    }

    @Test
    @Transactional
    void getAllStudentsByParentNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where parentNumber is not null
        defaultStudentShouldBeFound("parentNumber.specified=true");

        // Get all the studentList where parentNumber is null
        defaultStudentShouldNotBeFound("parentNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByParentNumberContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where parentNumber contains DEFAULT_PARENT_NUMBER
        defaultStudentShouldBeFound("parentNumber.contains=" + DEFAULT_PARENT_NUMBER);

        // Get all the studentList where parentNumber contains UPDATED_PARENT_NUMBER
        defaultStudentShouldNotBeFound("parentNumber.contains=" + UPDATED_PARENT_NUMBER);
    }

    @Test
    @Transactional
    void getAllStudentsByParentNumberNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where parentNumber does not contain DEFAULT_PARENT_NUMBER
        defaultStudentShouldNotBeFound("parentNumber.doesNotContain=" + DEFAULT_PARENT_NUMBER);

        // Get all the studentList where parentNumber does not contain UPDATED_PARENT_NUMBER
        defaultStudentShouldBeFound("parentNumber.doesNotContain=" + UPDATED_PARENT_NUMBER);
    }

    @Test
    @Transactional
    void getAllStudentsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where rate equals to DEFAULT_RATE
        defaultStudentShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the studentList where rate equals to UPDATED_RATE
        defaultStudentShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByRateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where rate not equals to DEFAULT_RATE
        defaultStudentShouldNotBeFound("rate.notEquals=" + DEFAULT_RATE);

        // Get all the studentList where rate not equals to UPDATED_RATE
        defaultStudentShouldBeFound("rate.notEquals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultStudentShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the studentList where rate equals to UPDATED_RATE
        defaultStudentShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where rate is not null
        defaultStudentShouldBeFound("rate.specified=true");

        // Get all the studentList where rate is null
        defaultStudentShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByRateContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where rate contains DEFAULT_RATE
        defaultStudentShouldBeFound("rate.contains=" + DEFAULT_RATE);

        // Get all the studentList where rate contains UPDATED_RATE
        defaultStudentShouldNotBeFound("rate.contains=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByRateNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where rate does not contain DEFAULT_RATE
        defaultStudentShouldNotBeFound("rate.doesNotContain=" + DEFAULT_RATE);

        // Get all the studentList where rate does not contain UPDATED_RATE
        defaultStudentShouldBeFound("rate.doesNotContain=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where address equals to DEFAULT_ADDRESS
        defaultStudentShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the studentList where address equals to UPDATED_ADDRESS
        defaultStudentShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllStudentsByAddressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where address not equals to DEFAULT_ADDRESS
        defaultStudentShouldNotBeFound("address.notEquals=" + DEFAULT_ADDRESS);

        // Get all the studentList where address not equals to UPDATED_ADDRESS
        defaultStudentShouldBeFound("address.notEquals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllStudentsByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultStudentShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the studentList where address equals to UPDATED_ADDRESS
        defaultStudentShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllStudentsByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where address is not null
        defaultStudentShouldBeFound("address.specified=true");

        // Get all the studentList where address is null
        defaultStudentShouldNotBeFound("address.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByAddressContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where address contains DEFAULT_ADDRESS
        defaultStudentShouldBeFound("address.contains=" + DEFAULT_ADDRESS);

        // Get all the studentList where address contains UPDATED_ADDRESS
        defaultStudentShouldNotBeFound("address.contains=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllStudentsByAddressNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where address does not contain DEFAULT_ADDRESS
        defaultStudentShouldNotBeFound("address.doesNotContain=" + DEFAULT_ADDRESS);

        // Get all the studentList where address does not contain UPDATED_ADDRESS
        defaultStudentShouldBeFound("address.doesNotContain=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllStudentsByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where remarks equals to DEFAULT_REMARKS
        defaultStudentShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the studentList where remarks equals to UPDATED_REMARKS
        defaultStudentShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllStudentsByRemarksIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where remarks not equals to DEFAULT_REMARKS
        defaultStudentShouldNotBeFound("remarks.notEquals=" + DEFAULT_REMARKS);

        // Get all the studentList where remarks not equals to UPDATED_REMARKS
        defaultStudentShouldBeFound("remarks.notEquals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllStudentsByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultStudentShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the studentList where remarks equals to UPDATED_REMARKS
        defaultStudentShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllStudentsByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where remarks is not null
        defaultStudentShouldBeFound("remarks.specified=true");

        // Get all the studentList where remarks is null
        defaultStudentShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByRemarksContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where remarks contains DEFAULT_REMARKS
        defaultStudentShouldBeFound("remarks.contains=" + DEFAULT_REMARKS);

        // Get all the studentList where remarks contains UPDATED_REMARKS
        defaultStudentShouldNotBeFound("remarks.contains=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllStudentsByRemarksNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where remarks does not contain DEFAULT_REMARKS
        defaultStudentShouldNotBeFound("remarks.doesNotContain=" + DEFAULT_REMARKS);

        // Get all the studentList where remarks does not contain UPDATED_REMARKS
        defaultStudentShouldBeFound("remarks.doesNotContain=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate equals to DEFAULT_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.equals=" + DEFAULT_JOINING_DATE);

        // Get all the studentList where joiningDate equals to UPDATED_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.equals=" + UPDATED_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate not equals to DEFAULT_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.notEquals=" + DEFAULT_JOINING_DATE);

        // Get all the studentList where joiningDate not equals to UPDATED_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.notEquals=" + UPDATED_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate in DEFAULT_JOINING_DATE or UPDATED_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.in=" + DEFAULT_JOINING_DATE + "," + UPDATED_JOINING_DATE);

        // Get all the studentList where joiningDate equals to UPDATED_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.in=" + UPDATED_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate is not null
        defaultStudentShouldBeFound("joiningDate.specified=true");

        // Get all the studentList where joiningDate is null
        defaultStudentShouldNotBeFound("joiningDate.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate is greater than or equal to DEFAULT_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.greaterThanOrEqual=" + DEFAULT_JOINING_DATE);

        // Get all the studentList where joiningDate is greater than or equal to UPDATED_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.greaterThanOrEqual=" + UPDATED_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate is less than or equal to DEFAULT_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.lessThanOrEqual=" + DEFAULT_JOINING_DATE);

        // Get all the studentList where joiningDate is less than or equal to SMALLER_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.lessThanOrEqual=" + SMALLER_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsLessThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate is less than DEFAULT_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.lessThan=" + DEFAULT_JOINING_DATE);

        // Get all the studentList where joiningDate is less than UPDATED_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.lessThan=" + UPDATED_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByJoiningDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where joiningDate is greater than DEFAULT_JOINING_DATE
        defaultStudentShouldNotBeFound("joiningDate.greaterThan=" + DEFAULT_JOINING_DATE);

        // Get all the studentList where joiningDate is greater than SMALLER_JOINING_DATE
        defaultStudentShouldBeFound("joiningDate.greaterThan=" + SMALLER_JOINING_DATE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price equals to DEFAULT_PRICE
        defaultStudentShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the studentList where price equals to UPDATED_PRICE
        defaultStudentShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price not equals to DEFAULT_PRICE
        defaultStudentShouldNotBeFound("price.notEquals=" + DEFAULT_PRICE);

        // Get all the studentList where price not equals to UPDATED_PRICE
        defaultStudentShouldBeFound("price.notEquals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultStudentShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the studentList where price equals to UPDATED_PRICE
        defaultStudentShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price is not null
        defaultStudentShouldBeFound("price.specified=true");

        // Get all the studentList where price is null
        defaultStudentShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price is greater than or equal to DEFAULT_PRICE
        defaultStudentShouldBeFound("price.greaterThanOrEqual=" + DEFAULT_PRICE);

        // Get all the studentList where price is greater than or equal to UPDATED_PRICE
        defaultStudentShouldNotBeFound("price.greaterThanOrEqual=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price is less than or equal to DEFAULT_PRICE
        defaultStudentShouldBeFound("price.lessThanOrEqual=" + DEFAULT_PRICE);

        // Get all the studentList where price is less than or equal to SMALLER_PRICE
        defaultStudentShouldNotBeFound("price.lessThanOrEqual=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price is less than DEFAULT_PRICE
        defaultStudentShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the studentList where price is less than UPDATED_PRICE
        defaultStudentShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByPriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where price is greater than DEFAULT_PRICE
        defaultStudentShouldNotBeFound("price.greaterThan=" + DEFAULT_PRICE);

        // Get all the studentList where price is greater than SMALLER_PRICE
        defaultStudentShouldBeFound("price.greaterThan=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    void getAllStudentsByCurrentSoraIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where currentSora equals to DEFAULT_CURRENT_SORA
        defaultStudentShouldBeFound("currentSora.equals=" + DEFAULT_CURRENT_SORA);

        // Get all the studentList where currentSora equals to UPDATED_CURRENT_SORA
        defaultStudentShouldNotBeFound("currentSora.equals=" + UPDATED_CURRENT_SORA);
    }

    @Test
    @Transactional
    void getAllStudentsByCurrentSoraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where currentSora not equals to DEFAULT_CURRENT_SORA
        defaultStudentShouldNotBeFound("currentSora.notEquals=" + DEFAULT_CURRENT_SORA);

        // Get all the studentList where currentSora not equals to UPDATED_CURRENT_SORA
        defaultStudentShouldBeFound("currentSora.notEquals=" + UPDATED_CURRENT_SORA);
    }

    @Test
    @Transactional
    void getAllStudentsByCurrentSoraIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where currentSora in DEFAULT_CURRENT_SORA or UPDATED_CURRENT_SORA
        defaultStudentShouldBeFound("currentSora.in=" + DEFAULT_CURRENT_SORA + "," + UPDATED_CURRENT_SORA);

        // Get all the studentList where currentSora equals to UPDATED_CURRENT_SORA
        defaultStudentShouldNotBeFound("currentSora.in=" + UPDATED_CURRENT_SORA);
    }

    @Test
    @Transactional
    void getAllStudentsByCurrentSoraIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where currentSora is not null
        defaultStudentShouldBeFound("currentSora.specified=true");

        // Get all the studentList where currentSora is null
        defaultStudentShouldNotBeFound("currentSora.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByCurrentSoraContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where currentSora contains DEFAULT_CURRENT_SORA
        defaultStudentShouldBeFound("currentSora.contains=" + DEFAULT_CURRENT_SORA);

        // Get all the studentList where currentSora contains UPDATED_CURRENT_SORA
        defaultStudentShouldNotBeFound("currentSora.contains=" + UPDATED_CURRENT_SORA);
    }

    @Test
    @Transactional
    void getAllStudentsByCurrentSoraNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where currentSora does not contain DEFAULT_CURRENT_SORA
        defaultStudentShouldNotBeFound("currentSora.doesNotContain=" + DEFAULT_CURRENT_SORA);

        // Get all the studentList where currentSora does not contain UPDATED_CURRENT_SORA
        defaultStudentShouldBeFound("currentSora.doesNotContain=" + UPDATED_CURRENT_SORA);
    }

    @Test
    @Transactional
    void getAllStudentsByStageIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where stage equals to DEFAULT_STAGE
        defaultStudentShouldBeFound("stage.equals=" + DEFAULT_STAGE);

        // Get all the studentList where stage equals to UPDATED_STAGE
        defaultStudentShouldNotBeFound("stage.equals=" + UPDATED_STAGE);
    }

    @Test
    @Transactional
    void getAllStudentsByStageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where stage not equals to DEFAULT_STAGE
        defaultStudentShouldNotBeFound("stage.notEquals=" + DEFAULT_STAGE);

        // Get all the studentList where stage not equals to UPDATED_STAGE
        defaultStudentShouldBeFound("stage.notEquals=" + UPDATED_STAGE);
    }

    @Test
    @Transactional
    void getAllStudentsByStageIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where stage in DEFAULT_STAGE or UPDATED_STAGE
        defaultStudentShouldBeFound("stage.in=" + DEFAULT_STAGE + "," + UPDATED_STAGE);

        // Get all the studentList where stage equals to UPDATED_STAGE
        defaultStudentShouldNotBeFound("stage.in=" + UPDATED_STAGE);
    }

    @Test
    @Transactional
    void getAllStudentsByStageIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where stage is not null
        defaultStudentShouldBeFound("stage.specified=true");

        // Get all the studentList where stage is null
        defaultStudentShouldNotBeFound("stage.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByStageContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where stage contains DEFAULT_STAGE
        defaultStudentShouldBeFound("stage.contains=" + DEFAULT_STAGE);

        // Get all the studentList where stage contains UPDATED_STAGE
        defaultStudentShouldNotBeFound("stage.contains=" + UPDATED_STAGE);
    }

    @Test
    @Transactional
    void getAllStudentsByStageNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where stage does not contain DEFAULT_STAGE
        defaultStudentShouldNotBeFound("stage.doesNotContain=" + DEFAULT_STAGE);

        // Get all the studentList where stage does not contain UPDATED_STAGE
        defaultStudentShouldBeFound("stage.doesNotContain=" + UPDATED_STAGE);
    }

    @Test
    @Transactional
    void getAllStudentsByGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where grade equals to DEFAULT_GRADE
        defaultStudentShouldBeFound("grade.equals=" + DEFAULT_GRADE);

        // Get all the studentList where grade equals to UPDATED_GRADE
        defaultStudentShouldNotBeFound("grade.equals=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    void getAllStudentsByGradeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where grade not equals to DEFAULT_GRADE
        defaultStudentShouldNotBeFound("grade.notEquals=" + DEFAULT_GRADE);

        // Get all the studentList where grade not equals to UPDATED_GRADE
        defaultStudentShouldBeFound("grade.notEquals=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    void getAllStudentsByGradeIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where grade in DEFAULT_GRADE or UPDATED_GRADE
        defaultStudentShouldBeFound("grade.in=" + DEFAULT_GRADE + "," + UPDATED_GRADE);

        // Get all the studentList where grade equals to UPDATED_GRADE
        defaultStudentShouldNotBeFound("grade.in=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    void getAllStudentsByGradeIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where grade is not null
        defaultStudentShouldBeFound("grade.specified=true");

        // Get all the studentList where grade is null
        defaultStudentShouldNotBeFound("grade.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByGradeContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where grade contains DEFAULT_GRADE
        defaultStudentShouldBeFound("grade.contains=" + DEFAULT_GRADE);

        // Get all the studentList where grade contains UPDATED_GRADE
        defaultStudentShouldNotBeFound("grade.contains=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    void getAllStudentsByGradeNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where grade does not contain DEFAULT_GRADE
        defaultStudentShouldNotBeFound("grade.doesNotContain=" + DEFAULT_GRADE);

        // Get all the studentList where grade does not contain UPDATED_GRADE
        defaultStudentShouldBeFound("grade.doesNotContain=" + UPDATED_GRADE);
    }

    @Test
    @Transactional
    void getAllStudentsByReadAndWriteRateIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where readAndWriteRate equals to DEFAULT_READ_AND_WRITE_RATE
        defaultStudentShouldBeFound("readAndWriteRate.equals=" + DEFAULT_READ_AND_WRITE_RATE);

        // Get all the studentList where readAndWriteRate equals to UPDATED_READ_AND_WRITE_RATE
        defaultStudentShouldNotBeFound("readAndWriteRate.equals=" + UPDATED_READ_AND_WRITE_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByReadAndWriteRateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where readAndWriteRate not equals to DEFAULT_READ_AND_WRITE_RATE
        defaultStudentShouldNotBeFound("readAndWriteRate.notEquals=" + DEFAULT_READ_AND_WRITE_RATE);

        // Get all the studentList where readAndWriteRate not equals to UPDATED_READ_AND_WRITE_RATE
        defaultStudentShouldBeFound("readAndWriteRate.notEquals=" + UPDATED_READ_AND_WRITE_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByReadAndWriteRateIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where readAndWriteRate in DEFAULT_READ_AND_WRITE_RATE or UPDATED_READ_AND_WRITE_RATE
        defaultStudentShouldBeFound("readAndWriteRate.in=" + DEFAULT_READ_AND_WRITE_RATE + "," + UPDATED_READ_AND_WRITE_RATE);

        // Get all the studentList where readAndWriteRate equals to UPDATED_READ_AND_WRITE_RATE
        defaultStudentShouldNotBeFound("readAndWriteRate.in=" + UPDATED_READ_AND_WRITE_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByReadAndWriteRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where readAndWriteRate is not null
        defaultStudentShouldBeFound("readAndWriteRate.specified=true");

        // Get all the studentList where readAndWriteRate is null
        defaultStudentShouldNotBeFound("readAndWriteRate.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByReadAndWriteRateContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where readAndWriteRate contains DEFAULT_READ_AND_WRITE_RATE
        defaultStudentShouldBeFound("readAndWriteRate.contains=" + DEFAULT_READ_AND_WRITE_RATE);

        // Get all the studentList where readAndWriteRate contains UPDATED_READ_AND_WRITE_RATE
        defaultStudentShouldNotBeFound("readAndWriteRate.contains=" + UPDATED_READ_AND_WRITE_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByReadAndWriteRateNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where readAndWriteRate does not contain DEFAULT_READ_AND_WRITE_RATE
        defaultStudentShouldNotBeFound("readAndWriteRate.doesNotContain=" + DEFAULT_READ_AND_WRITE_RATE);

        // Get all the studentList where readAndWriteRate does not contain UPDATED_READ_AND_WRITE_RATE
        defaultStudentShouldBeFound("readAndWriteRate.doesNotContain=" + UPDATED_READ_AND_WRITE_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByLastTestRateIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where lastTestRate equals to DEFAULT_LAST_TEST_RATE
        defaultStudentShouldBeFound("lastTestRate.equals=" + DEFAULT_LAST_TEST_RATE);

        // Get all the studentList where lastTestRate equals to UPDATED_LAST_TEST_RATE
        defaultStudentShouldNotBeFound("lastTestRate.equals=" + UPDATED_LAST_TEST_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByLastTestRateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where lastTestRate not equals to DEFAULT_LAST_TEST_RATE
        defaultStudentShouldNotBeFound("lastTestRate.notEquals=" + DEFAULT_LAST_TEST_RATE);

        // Get all the studentList where lastTestRate not equals to UPDATED_LAST_TEST_RATE
        defaultStudentShouldBeFound("lastTestRate.notEquals=" + UPDATED_LAST_TEST_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByLastTestRateIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where lastTestRate in DEFAULT_LAST_TEST_RATE or UPDATED_LAST_TEST_RATE
        defaultStudentShouldBeFound("lastTestRate.in=" + DEFAULT_LAST_TEST_RATE + "," + UPDATED_LAST_TEST_RATE);

        // Get all the studentList where lastTestRate equals to UPDATED_LAST_TEST_RATE
        defaultStudentShouldNotBeFound("lastTestRate.in=" + UPDATED_LAST_TEST_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByLastTestRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where lastTestRate is not null
        defaultStudentShouldBeFound("lastTestRate.specified=true");

        // Get all the studentList where lastTestRate is null
        defaultStudentShouldNotBeFound("lastTestRate.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByLastTestRateContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where lastTestRate contains DEFAULT_LAST_TEST_RATE
        defaultStudentShouldBeFound("lastTestRate.contains=" + DEFAULT_LAST_TEST_RATE);

        // Get all the studentList where lastTestRate contains UPDATED_LAST_TEST_RATE
        defaultStudentShouldNotBeFound("lastTestRate.contains=" + UPDATED_LAST_TEST_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByLastTestRateNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where lastTestRate does not contain DEFAULT_LAST_TEST_RATE
        defaultStudentShouldNotBeFound("lastTestRate.doesNotContain=" + DEFAULT_LAST_TEST_RATE);

        // Get all the studentList where lastTestRate does not contain UPDATED_LAST_TEST_RATE
        defaultStudentShouldBeFound("lastTestRate.doesNotContain=" + UPDATED_LAST_TEST_RATE);
    }

    @Test
    @Transactional
    void getAllStudentsByGroupClassIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where groupClass equals to DEFAULT_GROUP_CLASS
        defaultStudentShouldBeFound("groupClass.equals=" + DEFAULT_GROUP_CLASS);

        // Get all the studentList where groupClass equals to UPDATED_GROUP_CLASS
        defaultStudentShouldNotBeFound("groupClass.equals=" + UPDATED_GROUP_CLASS);
    }

    @Test
    @Transactional
    void getAllStudentsByGroupClassIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where groupClass not equals to DEFAULT_GROUP_CLASS
        defaultStudentShouldNotBeFound("groupClass.notEquals=" + DEFAULT_GROUP_CLASS);

        // Get all the studentList where groupClass not equals to UPDATED_GROUP_CLASS
        defaultStudentShouldBeFound("groupClass.notEquals=" + UPDATED_GROUP_CLASS);
    }

    @Test
    @Transactional
    void getAllStudentsByGroupClassIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where groupClass in DEFAULT_GROUP_CLASS or UPDATED_GROUP_CLASS
        defaultStudentShouldBeFound("groupClass.in=" + DEFAULT_GROUP_CLASS + "," + UPDATED_GROUP_CLASS);

        // Get all the studentList where groupClass equals to UPDATED_GROUP_CLASS
        defaultStudentShouldNotBeFound("groupClass.in=" + UPDATED_GROUP_CLASS);
    }

    @Test
    @Transactional
    void getAllStudentsByGroupClassIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where groupClass is not null
        defaultStudentShouldBeFound("groupClass.specified=true");

        // Get all the studentList where groupClass is null
        defaultStudentShouldNotBeFound("groupClass.specified=false");
    }

    @Test
    @Transactional
    void getAllStudentsByGroupClassContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where groupClass contains DEFAULT_GROUP_CLASS
        defaultStudentShouldBeFound("groupClass.contains=" + DEFAULT_GROUP_CLASS);

        // Get all the studentList where groupClass contains UPDATED_GROUP_CLASS
        defaultStudentShouldNotBeFound("groupClass.contains=" + UPDATED_GROUP_CLASS);
    }

    @Test
    @Transactional
    void getAllStudentsByGroupClassNotContainsSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where groupClass does not contain DEFAULT_GROUP_CLASS
        defaultStudentShouldNotBeFound("groupClass.doesNotContain=" + DEFAULT_GROUP_CLASS);

        // Get all the studentList where groupClass does not contain UPDATED_GROUP_CLASS
        defaultStudentShouldBeFound("groupClass.doesNotContain=" + UPDATED_GROUP_CLASS);
    }

    @Test
    @Transactional
    void getAllStudentsByDisConnectedIsEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where disConnected equals to DEFAULT_DIS_CONNECTED
        defaultStudentShouldBeFound("disConnected.equals=" + DEFAULT_DIS_CONNECTED);

        // Get all the studentList where disConnected equals to UPDATED_DIS_CONNECTED
        defaultStudentShouldNotBeFound("disConnected.equals=" + UPDATED_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void getAllStudentsByDisConnectedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where disConnected not equals to DEFAULT_DIS_CONNECTED
        defaultStudentShouldNotBeFound("disConnected.notEquals=" + DEFAULT_DIS_CONNECTED);

        // Get all the studentList where disConnected not equals to UPDATED_DIS_CONNECTED
        defaultStudentShouldBeFound("disConnected.notEquals=" + UPDATED_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void getAllStudentsByDisConnectedIsInShouldWork() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where disConnected in DEFAULT_DIS_CONNECTED or UPDATED_DIS_CONNECTED
        defaultStudentShouldBeFound("disConnected.in=" + DEFAULT_DIS_CONNECTED + "," + UPDATED_DIS_CONNECTED);

        // Get all the studentList where disConnected equals to UPDATED_DIS_CONNECTED
        defaultStudentShouldNotBeFound("disConnected.in=" + UPDATED_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void getAllStudentsByDisConnectedIsNullOrNotNull() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList where disConnected is not null
        defaultStudentShouldBeFound("disConnected.specified=true");

        // Get all the studentList where disConnected is null
        defaultStudentShouldNotBeFound("disConnected.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultStudentShouldBeFound(String filter) throws Exception {
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gander").value(hasItem(DEFAULT_GANDER)))
            .andExpect(jsonPath("$.[*].studyGroup").value(hasItem(DEFAULT_STUDY_GROUP)))
            .andExpect(jsonPath("$.[*].nationalId").value(hasItem(DEFAULT_NATIONAL_ID)))
            .andExpect(jsonPath("$.[*].parentNumber").value(hasItem(DEFAULT_PARENT_NUMBER)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].currentSora").value(hasItem(DEFAULT_CURRENT_SORA)))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].readAndWriteRate").value(hasItem(DEFAULT_READ_AND_WRITE_RATE)))
            .andExpect(jsonPath("$.[*].lastTestRate").value(hasItem(DEFAULT_LAST_TEST_RATE)))
            .andExpect(jsonPath("$.[*].groupClass").value(hasItem(DEFAULT_GROUP_CLASS)))
            .andExpect(jsonPath("$.[*].disConnected").value(hasItem(DEFAULT_DIS_CONNECTED.booleanValue())));

        // Check, that the count call also returns 1
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultStudentShouldNotBeFound(String filter) throws Exception {
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restStudentMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .name(UPDATED_NAME)
            .age(UPDATED_AGE)
            .gander(UPDATED_GANDER)
            .studyGroup(UPDATED_STUDY_GROUP)
            .nationalId(UPDATED_NATIONAL_ID)
            .parentNumber(UPDATED_PARENT_NUMBER)
            .rate(UPDATED_RATE)
            .address(UPDATED_ADDRESS)
            .remarks(UPDATED_REMARKS)
            .joiningDate(UPDATED_JOINING_DATE)
            .price(UPDATED_PRICE)
            .currentSora(UPDATED_CURRENT_SORA)
            .stage(UPDATED_STAGE)
            .grade(UPDATED_GRADE)
            .readAndWriteRate(UPDATED_READ_AND_WRITE_RATE)
            .lastTestRate(UPDATED_LAST_TEST_RATE)
            .groupClass(UPDATED_GROUP_CLASS)
            .disConnected(UPDATED_DIS_CONNECTED);
        StudentDTO studentDTO = studentMapper.toDto(updatedStudent);

        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, studentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentDTO))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testStudent.getGander()).isEqualTo(UPDATED_GANDER);
        assertThat(testStudent.getStudyGroup()).isEqualTo(UPDATED_STUDY_GROUP);
        assertThat(testStudent.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testStudent.getParentNumber()).isEqualTo(UPDATED_PARENT_NUMBER);
        assertThat(testStudent.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testStudent.getJoiningDate()).isEqualTo(UPDATED_JOINING_DATE);
        assertThat(testStudent.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testStudent.getCurrentSora()).isEqualTo(UPDATED_CURRENT_SORA);
        assertThat(testStudent.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testStudent.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testStudent.getReadAndWriteRate()).isEqualTo(UPDATED_READ_AND_WRITE_RATE);
        assertThat(testStudent.getLastTestRate()).isEqualTo(UPDATED_LAST_TEST_RATE);
        assertThat(testStudent.getGroupClass()).isEqualTo(UPDATED_GROUP_CLASS);
        assertThat(testStudent.getDisConnected()).isEqualTo(UPDATED_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void putNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, studentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(studentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent
            .name(UPDATED_NAME)
            .nationalId(UPDATED_NATIONAL_ID)
            .rate(UPDATED_RATE)
            .address(UPDATED_ADDRESS)
            .remarks(UPDATED_REMARKS)
            .currentSora(UPDATED_CURRENT_SORA)
            .stage(UPDATED_STAGE)
            .grade(UPDATED_GRADE)
            .disConnected(UPDATED_DIS_CONNECTED);

        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testStudent.getGander()).isEqualTo(DEFAULT_GANDER);
        assertThat(testStudent.getStudyGroup()).isEqualTo(DEFAULT_STUDY_GROUP);
        assertThat(testStudent.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testStudent.getParentNumber()).isEqualTo(DEFAULT_PARENT_NUMBER);
        assertThat(testStudent.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testStudent.getJoiningDate()).isEqualTo(DEFAULT_JOINING_DATE);
        assertThat(testStudent.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testStudent.getCurrentSora()).isEqualTo(UPDATED_CURRENT_SORA);
        assertThat(testStudent.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testStudent.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testStudent.getReadAndWriteRate()).isEqualTo(DEFAULT_READ_AND_WRITE_RATE);
        assertThat(testStudent.getLastTestRate()).isEqualTo(DEFAULT_LAST_TEST_RATE);
        assertThat(testStudent.getGroupClass()).isEqualTo(DEFAULT_GROUP_CLASS);
        assertThat(testStudent.getDisConnected()).isEqualTo(UPDATED_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void fullUpdateStudentWithPatch() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student using partial update
        Student partialUpdatedStudent = new Student();
        partialUpdatedStudent.setId(student.getId());

        partialUpdatedStudent
            .name(UPDATED_NAME)
            .age(UPDATED_AGE)
            .gander(UPDATED_GANDER)
            .studyGroup(UPDATED_STUDY_GROUP)
            .nationalId(UPDATED_NATIONAL_ID)
            .parentNumber(UPDATED_PARENT_NUMBER)
            .rate(UPDATED_RATE)
            .address(UPDATED_ADDRESS)
            .remarks(UPDATED_REMARKS)
            .joiningDate(UPDATED_JOINING_DATE)
            .price(UPDATED_PRICE)
            .currentSora(UPDATED_CURRENT_SORA)
            .stage(UPDATED_STAGE)
            .grade(UPDATED_GRADE)
            .readAndWriteRate(UPDATED_READ_AND_WRITE_RATE)
            .lastTestRate(UPDATED_LAST_TEST_RATE)
            .groupClass(UPDATED_GROUP_CLASS)
            .disConnected(UPDATED_DIS_CONNECTED);

        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudent))
            )
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudent.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testStudent.getGander()).isEqualTo(UPDATED_GANDER);
        assertThat(testStudent.getStudyGroup()).isEqualTo(UPDATED_STUDY_GROUP);
        assertThat(testStudent.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testStudent.getParentNumber()).isEqualTo(UPDATED_PARENT_NUMBER);
        assertThat(testStudent.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testStudent.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStudent.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testStudent.getJoiningDate()).isEqualTo(UPDATED_JOINING_DATE);
        assertThat(testStudent.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testStudent.getCurrentSora()).isEqualTo(UPDATED_CURRENT_SORA);
        assertThat(testStudent.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testStudent.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testStudent.getReadAndWriteRate()).isEqualTo(UPDATED_READ_AND_WRITE_RATE);
        assertThat(testStudent.getLastTestRate()).isEqualTo(UPDATED_LAST_TEST_RATE);
        assertThat(testStudent.getGroupClass()).isEqualTo(UPDATED_GROUP_CLASS);
        assertThat(testStudent.getDisConnected()).isEqualTo(UPDATED_DIS_CONNECTED);
    }

    @Test
    @Transactional
    void patchNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, studentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(studentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(studentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();
        student.setId(count.incrementAndGet());

        // Create the Student
        StudentDTO studentDTO = studentMapper.toDto(student);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(studentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Delete the student
        restStudentMockMvc
            .perform(delete(ENTITY_API_URL_ID, student.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
