package com.example.demo.commune;

import com.example.demo.commune.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommuneRepository extends JpaRepository<Commune, UUID> {
    String str_AnyField = "select* from commune where " +
            "(:name is null or LOWER(name) like  CONCAT('%',CONCAT(LOWER(:name),'%')) collate binary_ai)";
    @Query(value = str_AnyField, nativeQuery = true)
    List<Commune> findAllByName(String name);
}
