package com.akol.finalspring.controller;

import com.akol.finalspring.dao.CategorieBd;
import com.akol.finalspring.entity.Categorie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private CategorieBd categorieBd;

    @GetMapping("/gestion")
    public String gestion() {
        return "categorie.gestion";
    }

    @GetMapping("/liste")
    public String liste(Model model) {
        model.addAttribute("liste", categorieBd.findAllCategories());
        return "categorie.liste";
    }

    @GetMapping("/add")
    public String ajout(Model model) {
        Categorie categorie = new Categorie();
        model.addAttribute("categorie", categorie);
        return "categorie.ajout";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("categorie") Categorie categorie, BindingResult result){
        if(result.hasErrors()){
            return "categorie.ajout";
        }
        categorieBd.saveCategory(categorie);
        return "redirect:/categorie/liste";
    }

    @GetMapping("/supprimer/{code}")
    public String delete(@PathVariable("code") String code){
        categorieBd.deleteCategory(code);
        return "redirect:/categorie/liste";
    }

    @GetMapping("/modifier/{code}")
    public String modifier(@PathVariable("code") String code, Model model){
        Categorie categorie = categorieBd.findCategory(code);
        if(categorie == null){
            return "redirect:/categorie/liste";
        }
        model.addAttribute("categorie", categorie);
        return "categorie.modifier";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("categorie") Categorie categorie, BindingResult result){
        if(result.hasErrors()){
            return "categorie.modifier";
        }
        categorieBd.saveCategory(categorie);
        return "redirect:/categorie/liste";
    }

}
