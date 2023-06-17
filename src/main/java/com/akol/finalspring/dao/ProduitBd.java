package com.akol.finalspring.dao;

import com.akol.finalspring.entity.Categorie;
import com.akol.finalspring.entity.Produit;
import com.akol.finalspring.repository.CategorieRepository;
import com.akol.finalspring.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitBd {
    @Autowired
    private ProduitRepository produitRepo;

    @Transactional
    public void saveProduct(Produit produit){
        produitRepo.save(produit);
    }

    @Transactional
    public void deleteProduct(Integer code){
       Optional<Produit> produit = produitRepo.findById(code);
        if(produit != null){
            produitRepo.deleteById(code);
        }
    }

    public Produit findProduct(Integer code){
        Optional<Produit> produit = produitRepo.findById(code);
        if(produit.isPresent()){
            return produit.get();
        }
        return null;
    }

    public List<Produit> findAllProducts(){
        return produitRepo.findAll();
    }
}
