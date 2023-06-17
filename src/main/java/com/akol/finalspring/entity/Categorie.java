package com.akol.finalspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {
    @Id
    @NotEmpty(message = "Le code de la categorie est obligatoire")
    @Column(length = 25)
    private String code;
    @Column
    @NotEmpty(message = "Le libelle de la categorie est obligatoire")
    private String libelle;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Produit> productsList = new ArrayList<Produit>();

    public Categorie() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Produit> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Produit> productsList) {
        this.productsList = productsList;
    }
}
