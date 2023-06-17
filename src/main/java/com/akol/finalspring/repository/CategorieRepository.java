package com.akol.finalspring.repository;

import com.akol.finalspring.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,String> {
    public Categorie findByCode(String code);
}
