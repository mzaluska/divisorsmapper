package com.maza.divisorsmapper.repository;

import com.maza.divisorsmapper.model.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MappingRepository extends JpaRepository<Mapping, Long> {

    @Query("select a from Mapping a join fetch a.category where a.category.name = ?1")
    List<Mapping> findAllByCategoryName(String category);

}
