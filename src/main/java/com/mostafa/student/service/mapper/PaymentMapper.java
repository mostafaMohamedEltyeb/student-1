package com.mostafa.student.service.mapper;

import com.mostafa.student.domain.*;
import com.mostafa.student.service.dto.PaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = { StudentMapper.class })
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
//    @Mapping(target = "studentId", source = "studentId", qualifiedByName = "no")
    PaymentDTO toDto(Payment s);
}
