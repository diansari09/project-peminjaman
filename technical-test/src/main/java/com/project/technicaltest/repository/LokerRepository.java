package com.project.technicaltest.repository;


import com.project.technicaltest.model.Loker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokerRepository extends JpaRepository<Loker, Long> {
}
