package com.kira.shop.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categories.class)
public abstract class Categories_ {

	public static volatile SingularAttribute<Categories, String> catName;
	public static volatile SetAttribute<Categories, Products> catProducts;
	public static volatile SingularAttribute<Categories, Integer> catOrder;
	public static volatile SingularAttribute<Categories, Long> id;
	public static volatile SingularAttribute<Categories, String> catDescription;

	public static final String CAT_NAME = "catName";
	public static final String CAT_PRODUCTS = "catProducts";
	public static final String CAT_ORDER = "catOrder";
	public static final String ID = "id";
	public static final String CAT_DESCRIPTION = "catDescription";

}

