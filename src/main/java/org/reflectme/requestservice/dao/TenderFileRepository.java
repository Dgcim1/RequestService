package org.reflectme.requestservice.dao;

import org.reflectme.requestservice.entity.TenderFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenderFileRepository extends JpaRepository<TenderFile, Long> {
    @Query("SELECT tf FROM TenderFile tf WHERE tf.tender.id = ?1")
    List<TenderFile> customFindAllByTenderId(Long tenderId);
}
