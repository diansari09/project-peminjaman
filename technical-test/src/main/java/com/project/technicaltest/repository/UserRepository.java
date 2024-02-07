package com.project.technicaltest.repository;


import com.project.technicaltest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    public Users findByKtp(String ktp);

    public Users findByEmail(String email);
}
