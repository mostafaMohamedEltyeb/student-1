package com.mostafa.student.repository;

import com.mostafa.student.domain.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Payment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {

    @Query(value = "select p from Payment p where p.studentId.id in (select s.id from Student s where s.name like :studentName) and (:month = 0 or p.month =:month) and (:year = 0 or p.year =:year)")
    Optional<List<Payment>> findPayment(String studentName, Integer month, Integer year, Pageable pageable);
}
