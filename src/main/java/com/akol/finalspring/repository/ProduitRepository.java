package com.akol.finalspring.repository;

import com.akol.finalspring.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Integer>{
    public Produit findByNumero(Integer numero);
}
