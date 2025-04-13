package org.example.advancedrealestate_be.repository;

import org.example.advancedrealestate_be.entity.Customers;
import org.example.advancedrealestate_be.entity.Maintenances;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenances,String> {
    Page<Maintenances> findAll(Pageable pageable);
    boolean existsByBuildingId(String buildingId);
}
