package com.example.ec.repo;

import com.example.ec.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TourPackageRepository extends CrudRepository<TourPackage,String> {
    Optional<TourPackage> findByName(@Param("name")String name);
}
