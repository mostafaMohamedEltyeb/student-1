package com.mostafa.student.repository;

import com.mostafa.student.domain.Payment;
import com.mostafa.student.domain.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Student entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {


    @Query(value = "SELECT s FROM Student s WHERE s.id NOT IN (SELECT p.studentId.id FROM Payment p WHERE p.month =:month and p.year =:year)")
    Optional<List<Student>> latePayment(Integer month, Integer year, Pageable pageable);

    @Query(value = "select p from Student p where  p.name like :name and (:studyGroup = 'zzNull' or  p.studyGroup = :studyGroup ) and  ((:ageFrom = 0 and :ageTo = 0 ) or ( p.age between :ageFrom and :ageTo )) and (:gander =3 or p.gander = :gander ) and p.disConnected = :disconnected")
    Optional<List<Student>> filterStudent(String name, String studyGroup, Integer ageFrom, Integer ageTo, Integer gander, Boolean disconnected, Pageable pageable);

}
