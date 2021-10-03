package com.kira.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Products.
 */
@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pdt_name")
    private String pdtName;

    @Column(name = "pdt_subtitle")
    private String pdtSubtitle;

    @Column(name = "pdt_description")
    private String pdtDescription;

    @Column(name = "pdt_material")
    private String pdtMaterial;

    @Column(name = "pdt_price")
    private Integer pdtPrice;

    @Column(name = "pdt_stock")
    private Integer pdtStock;

    @Column(name = "pdt_on_sold")
    private Boolean pdtOnSold;

    @ManyToOne
    @JsonIgnoreProperties(value = "catProducts", allowSetters = true)
    private Categories pdtCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPdtName() {
        return pdtName;
    }

    public Products pdtName(String pdtName) {
        this.pdtName = pdtName;
        return this;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    public String getPdtSubtitle() {
        return pdtSubtitle;
    }

    public Products pdtSubtitle(String pdtSubtitle) {
        this.pdtSubtitle = pdtSubtitle;
        return this;
    }

    public void setPdtSubtitle(String pdtSubtitle) {
        this.pdtSubtitle = pdtSubtitle;
    }

    public String getPdtDescription() {
        return pdtDescription;
    }

    public Products pdtDescription(String pdtDescription) {
        this.pdtDescription = pdtDescription;
        return this;
    }

    public void setPdtDescription(String pdtDescription) {
        this.pdtDescription = pdtDescription;
    }

    public String getPdtMaterial() {
        return pdtMaterial;
    }

    public Products pdtMaterial(String pdtMaterial) {
        this.pdtMaterial = pdtMaterial;
        return this;
    }

    public void setPdtMaterial(String pdtMaterial) {
        this.pdtMaterial = pdtMaterial;
    }

    public Integer getPdtPrice() {
        return pdtPrice;
    }

    public Products pdtPrice(Integer pdtPrice) {
        this.pdtPrice = pdtPrice;
        return this;
    }

    public void setPdtPrice(Integer pdtPrice) {
        this.pdtPrice = pdtPrice;
    }

    public Integer getPdtStock() {
        return pdtStock;
    }

    public Products pdtStock(Integer pdtStock) {
        this.pdtStock = pdtStock;
        return this;
    }

    public void setPdtStock(Integer pdtStock) {
        this.pdtStock = pdtStock;
    }

    public Boolean isPdtOnSold() {
        return pdtOnSold;
    }

    public Products pdtOnSold(Boolean pdtOnSold) {
        this.pdtOnSold = pdtOnSold;
        return this;
    }

    public void setPdtOnSold(Boolean pdtOnSold) {
        this.pdtOnSold = pdtOnSold;
    }

    public Categories getPdtCategory() {
        return pdtCategory;
    }

    public Products pdtCategory(Categories categories) {
        this.pdtCategory = categories;
        return this;
    }

    public void setPdtCategory(Categories categories) {
        this.pdtCategory = categories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Products)) {
            return false;
        }
        return id != null && id.equals(((Products) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Products{" +
            "id=" + getId() +
            ", pdtName='" + getPdtName() + "'" +
            ", pdtSubtitle='" + getPdtSubtitle() + "'" +
            ", pdtDescription='" + getPdtDescription() + "'" +
            ", pdtMaterial='" + getPdtMaterial() + "'" +
            ", pdtPrice=" + getPdtPrice() +
            ", pdtStock=" + getPdtStock() +
            ", pdtOnSold='" + isPdtOnSold() + "'" +
            "}";
    }
}
