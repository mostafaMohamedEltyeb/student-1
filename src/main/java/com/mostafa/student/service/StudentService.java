package com.mostafa.student.service;

import com.mostafa.student.domain.Student;
import com.mostafa.student.repository.StudentRepository;
import com.mostafa.student.service.dto.StudentDTO;
import com.mostafa.student.service.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Student}.
 */
@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public Optional<StudentDTO> partialUpdate(StudentDTO studentDTO) {
        log.debug("Request to partially update Student : {}", studentDTO);

        return studentRepository
            .findById(studentDTO.getId())
            .map(
                existingStudent -> {
                    studentMapper.partialUpdate(existingStudent, studentDTO);
                    return existingStudent;
                }
            )
            .map(studentRepository::save)
            .map(studentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<StudentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Students");
        return studentRepository.findAll(pageable).map(studentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<StudentDTO> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id).map(studentMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }

    public Optional<List<StudentDTO>> latePayment(Integer month, Integer year, Pageable pageable) {
        return studentRepository.latePayment(month,year,pageable).map(studentMapper::toDto);
    }

    public Optional<List<StudentDTO>> filterStudent(String name, String studyGroup, Integer ageFrom, Integer ageTo, Integer gander, Boolean disconnected, Pageable pageable) {
        if (name == null || name.equals("zzNull")) {
            name = "%%";
        } else {
            name = "%" + name + "%";
        }
        return studentRepository.filterStudent(name, studyGroup, ageFrom, ageTo, gander, disconnected, pageable).map(studentMapper::toDto);
    }
}
