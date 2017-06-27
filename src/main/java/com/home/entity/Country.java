package com.home.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nazar on 24.06.2017.
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {
    static final long serialVersionUID = 9156742230092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "country_name")
    private String name;

    @Column(name = "language")
    private String language;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    public Set<Location> locations;

    public Country() {
    }

    public Country(String name, String language, Set<Location> locations) {
        this.name = name;
        this.language = language;
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
