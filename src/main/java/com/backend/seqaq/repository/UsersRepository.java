package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByAccount(String account);
}
