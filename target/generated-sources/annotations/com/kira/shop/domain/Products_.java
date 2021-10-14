package com.kira.shop.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Products.class)
public abstract class Products_ {

	public static volatile SingularAttribute<Products, String> pdtSubtitle;
	public static volatile SingularAttribute<Products, Categories> pdtCategory;
	public static volatile SingularAttribute<Products, String> pdtDescription;
	public static volatile SingularAttribute<Products, Integer> pdtPrice;
	public static volatile SingularAttribute<Products, String> pdtMaterial;
	public static volatile SingularAttribute<Products, Integer> pdtStock;
	public static volatile SingularAttribute<Products, Long> id;
	public static volatile SingularAttribute<Products, String> pdtName;
	public static volatile SingularAttribute<Products, Boolean> pdtOnSold;

	public static final String PDT_SUBTITLE = "pdtSubtitle";
	public static final String PDT_CATEGORY = "pdtCategory";
	public static final String PDT_DESCRIPTION = "pdtDescription";
	public static final String PDT_PRICE = "pdtPrice";
	public static final String PDT_MATERIAL = "pdtMaterial";
	public static final String PDT_STOCK = "pdtStock";
	public static final String ID = "id";
	public static final String PDT_NAME = "pdtName";
	public static final String PDT_ON_SOLD = "pdtOnSold";

}

