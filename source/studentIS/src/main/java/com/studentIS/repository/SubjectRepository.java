package com.studentIS.repository;

import com.studentIS.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * FROM subjects WHERE name LIKE ?1", nativeQuery = true)
    List<Subject> findByName(String name);
}
