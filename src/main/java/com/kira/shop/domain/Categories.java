package com.kira.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Categories.
 */
@Entity
@Table(name = "categories")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "cat_description")
    private String catDescription;

    @Column(name = "cat_order")
    private Integer catOrder;

    @OneToMany(mappedBy = "pdtCategory")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Products> catProducts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public Categories catName(String catName) {
        this.catName = catName;
        return this;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public Categories catDescription(String catDescription) {
        this.catDescription = catDescription;
        return this;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public Integer getCatOrder() {
        return catOrder;
    }

    public Categories catOrder(Integer catOrder) {
        this.catOrder = catOrder;
        return this;
    }

    public void setCatOrder(Integer catOrder) {
        this.catOrder = catOrder;
    }

    public Set<Products> getCatProducts() {
        return catProducts;
    }

    public Categories catProducts(Set<Products> Products) {
        this.catProducts = Products;
        return this;
    }

    public Categories addCatProducts(Products Products) {
        this.catProducts.add(Products);
        Products.setPdtCategory(this);
        return this;
    }

    public Categories removeCatProducts(Products Products) {
        this.catProducts.remove(Products);
        Products.setPdtCategory(null);
        return this;
    }

    public void setCatProducts(Set<Products> Products) {
        this.catProducts = Products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categories)) {
            return false;
        }
        return id != null && id.equals(((Categories) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Categories{" +
            "id=" + getId() +
            ", catName='" + getCatName() + "'" +
            ", catDescription='" + getCatDescription() + "'" +
            ", catOrder=" + getCatOrder() +
            "}";
    }
}
