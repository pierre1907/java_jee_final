package com.akol.finalspring.dao;

import com.akol.finalspring.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.akol.finalspring.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorieBd {
    @Autowired
    private CategorieRepository cateRepo;

    @Transactional
    public void saveCategory(Categorie categorie){
        cateRepo.save(categorie);
    }

    @Transactional
    public void deleteCategory(String code){
        //Recuperer la categorie
        Categorie categorie = cateRepo.findByCode(code);
        //Verifier si la categorie existe
        if(categorie != null){
            //Supprimer la categorie
            cateRepo.delete(categorie);
        }
    }

    public Categorie findCategory(String code){
        return cateRepo.findByCode(code);
    }

    public List<Categorie> findAllCategories(){
        return cateRepo.findAll();
    }
}
