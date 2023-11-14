package org.reflectme.requestservice.dao;

import org.reflectme.requestservice.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender, Long> {
    List<Tender> findAllByActiveIsTrue();
}
