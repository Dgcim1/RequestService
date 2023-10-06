package org.reflectme.requestservice.dao;

import org.reflectme.requestservice.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByActiveIsTrue();
}
