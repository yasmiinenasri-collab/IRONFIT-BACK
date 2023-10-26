<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nasri
 */
public class CodePromo { private int id_codepromo;
    private String code;
    private String description;
    private String datedexpiration;
    private String used ;
    private User user;

    public CodePromo() {
    }

    public CodePromo(int id_codepromo, String code, String description,String datedexpiration, String used ) {
        this.id_codepromo = id_codepromo;
        this.code = code;
        this.description = description;
        this.datedexpiration = datedexpiration;
        this.used = used;
    }

    public String getDatedexpiration() {
        return datedexpiration;
    }

    public void setDatedexpiration(String datedexpiration) {
        this.datedexpiration = datedexpiration;
    }

    public CodePromo(String code, String description, String datedexpiration, String used) {
        this.code = code;
        this.description = description;
        this.datedexpiration = datedexpiration;
        this.used = used;
    }

    public CodePromo(String code, String description, String dateExpiration) {
        this.code = code;
        this.description = description;
       
    }

    public int getId_codepromo() {
        return id_codepromo;
    }

    public void setId_codepromo(int id_codepromo) {
        this.id_codepromo = id_codepromo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String isUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "CodePromo{" + "id_codepromo=" + id_codepromo + ", code=" + code + ", description=" + description +   ", Date d'expiration="+datedexpiration +", used=" + used + '}';
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
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author nasri
 */
public class CodePromo { private int id_codepromo;
    private String code;
    private String description;
    private String datedexpiration;
    private String used ;
    private User user;

    public CodePromo() {
    }

    public CodePromo(int id_codepromo, String code, String description,String datedexpiration, String used ) {
        this.id_codepromo = id_codepromo;
        this.code = code;
        this.description = description;
        this.datedexpiration = datedexpiration;
        this.used = used;
    }

    public String getDatedexpiration() {
        return datedexpiration;
    }

    public void setDatedexpiration(String datedexpiration) {
        this.datedexpiration = datedexpiration;
    }

    public CodePromo(String code, String description, String datedexpiration, String used) {
        this.code = code;
        this.description = description;
        this.datedexpiration = datedexpiration;
        this.used = used;
    }

    public CodePromo(String code, String description, String dateExpiration) {
        this.code = code;
        this.description = description;
       
    }

    public int getId_codepromo() {
        return id_codepromo;
    }

    public void setId_codepromo(int id_codepromo) {
        this.id_codepromo = id_codepromo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String isUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "CodePromo{" + "id_codepromo=" + id_codepromo + ", code=" + code + ", description=" + description +   ", Date d'expiration="+datedexpiration +", used=" + used + '}';
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
>>>>>>> 595d8441276fcc8a656ad5e3c28f2e4ca6806126
