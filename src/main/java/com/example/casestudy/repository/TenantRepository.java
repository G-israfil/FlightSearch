package com.example.casestudy.repository;

import com.example.casestudy.entity.Tenant;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer> {
    Tenant findTenantByUsername(@NonNull String username);
}
