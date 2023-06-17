package com.akol.finalspring.controller;

import com.akol.finalspring.dao.CategorieBd;
import com.akol.finalspring.dao.ProduitBd;
import com.akol.finalspring.entity.Categorie;
import com.akol.finalspring.entity.Produit;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    private ProduitBd produitBd;
    @Autowired
    private CategorieBd categorieBd;

    @GetMapping("/gestion")
    public String gestion(){
        return "produit.gestion";
    }

    @GetMapping("/liste")
    public String liste(Model model){
        model.addAttribute("liste", produitBd.findAllProducts());
        return "produit.liste";
    }

    @GetMapping("/add")
    public String ajout(Model model){
        Produit produit = new Produit();
        model.addAttribute("produit", produit);
        model.addAttribute("categories", categorieBd.findAllCategories());
        return "produit.ajout";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("produit") Produit produit, BindingResult result){
        if(result.hasErrors()){
            return "produit.ajout";
        }
        Categorie categorie = categorieBd.findCategory(produit.getCodeCat());
        produit.setCategory(categorie);
        produitBd.saveProduct(produit);
        return "redirect:/produit/liste";
    }

    @GetMapping("/supprimer/{numero}")
    public String delete(@PathVariable Integer numero){
        produitBd.deleteProduct(numero);
        return "redirect:/produit/liste";
    }

    @GetMapping("/modifier/{numero}")
    public String modifier(@PathVariable Integer numero, Model model){
        Produit produit = produitBd.findProduct(numero);
        if(produit == null){
            return "redirect:/produit/liste";
        }
        model.addAttribute("produit", produit);
        model.addAttribute("categories", categorieBd.findAllCategories());
        return "produit.modifier";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("produit") Produit produit, BindingResult result){
        if(result.hasErrors()){
            return "produit.modifier";
        }
        Categorie categorie = categorieBd.findCategory(produit.getCodeCat());
        produit.setCategory(categorie);
        produitBd.saveProduct(produit);
        return "redirect:/produit/liste";
    }
}
