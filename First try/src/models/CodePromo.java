/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entite;

import java.time.LocalDate;

/**
 *
 * @author nasri
 */
public class CodePromo {
    private int id_codepromo;
    private Long code;
    private String description;
    private String datedexpiration;
    private String used ;
    private Utilisateur utilisateur;

    public CodePromo() {
    }

    public CodePromo(int id_codepromo, Long code, String description,String datedexpiration, String used) {
        this.id_codepromo = id_codepromo;
        this.code = code;
        this.description = description;
        this.datedexpiration = datedexpiration;
        this.used = used;
    }

    public CodePromo(Long code, String description, String datedexpiration, String used) {
        this.code = code;
        this.description = description;
        this.datedexpiration = datedexpiration;
        this.used = used;
    }

    public int getId_codepromo() {
        return id_codepromo;
    }

    public void setId_codepromo(int id_codepromo) {
        this.id_codepromo = id_codepromo;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatedexpiration() {
        return datedexpiration;
    }

    public void setDatedexpiration(String datedexpiration) {
        this.datedexpiration = datedexpiration;
    }

    public String isUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "CodePromo{" + "id_codepromo=" + id_codepromo + ", code=" + code + ", description=" + description + ", datedexpiration=" + datedexpiration + ", used=" + used + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id_codepromo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CodePromo other = (CodePromo) obj;
        return this.id_codepromo == other.id_codepromo;
    }
    
   
}

