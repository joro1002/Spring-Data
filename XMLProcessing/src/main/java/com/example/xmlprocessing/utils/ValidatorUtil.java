package com.example.xmlprocessing.utils;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.mapping.Constraint;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {
    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> violations(E entity);
}
