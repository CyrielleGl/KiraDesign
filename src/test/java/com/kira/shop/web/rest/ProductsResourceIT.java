package com.kira.shop.web.rest;

import com.kira.shop.KiraApp;
import com.kira.shop.domain.Products;
import com.kira.shop.repository.ProductsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductsResource} REST controller.
 */
@SpringBootTest(classes = KiraApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductsResourceIT {

    private static final String DEFAULT_PDT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PDT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PDT_SUBTITLE = "AAAAAAAAAA";
    private static final String UPDATED_PDT_SUBTITLE = "BBBBBBBBBB";

    private static final String DEFAULT_PDT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PDT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PDT_MATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_PDT_MATERIAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_PDT_PRICE = 1;
    private static final Integer UPDATED_PDT_PRICE = 2;

    private static final Integer DEFAULT_PDT_STOCK = 1;
    private static final Integer UPDATED_PDT_STOCK = 2;

    private static final Boolean DEFAULT_PDT_ON_SOLD = false;
    private static final Boolean UPDATED_PDT_ON_SOLD = true;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductsMockMvc;

    private Products products;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Products createEntity(EntityManager em) {
        Products products = new Products()
            .pdtName(DEFAULT_PDT_NAME)
            .pdtSubtitle(DEFAULT_PDT_SUBTITLE)
            .pdtDescription(DEFAULT_PDT_DESCRIPTION)
            .pdtMaterial(DEFAULT_PDT_MATERIAL)
            .pdtPrice(DEFAULT_PDT_PRICE)
            .pdtStock(DEFAULT_PDT_STOCK)
            .pdtOnSold(DEFAULT_PDT_ON_SOLD);
        return products;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Products createUpdatedEntity(EntityManager em) {
        Products products = new Products()
            .pdtName(UPDATED_PDT_NAME)
            .pdtSubtitle(UPDATED_PDT_SUBTITLE)
            .pdtDescription(UPDATED_PDT_DESCRIPTION)
            .pdtMaterial(UPDATED_PDT_MATERIAL)
            .pdtPrice(UPDATED_PDT_PRICE)
            .pdtStock(UPDATED_PDT_STOCK)
            .pdtOnSold(UPDATED_PDT_ON_SOLD);
        return products;
    }

    @BeforeEach
    public void initTest() {
        products = createEntity(em);
    }

    @Test
    @Transactional
    public void createProducts() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();
        // Create the Products
        restProductsMockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(products)))
            .andExpect(status().isCreated());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate + 1);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getPdtName()).isEqualTo(DEFAULT_PDT_NAME);
        assertThat(testProducts.getPdtSubtitle()).isEqualTo(DEFAULT_PDT_SUBTITLE);
        assertThat(testProducts.getPdtDescription()).isEqualTo(DEFAULT_PDT_DESCRIPTION);
        assertThat(testProducts.getPdtMaterial()).isEqualTo(DEFAULT_PDT_MATERIAL);
        assertThat(testProducts.getPdtPrice()).isEqualTo(DEFAULT_PDT_PRICE);
        assertThat(testProducts.getPdtStock()).isEqualTo(DEFAULT_PDT_STOCK);
        assertThat(testProducts.isPdtOnSold()).isEqualTo(DEFAULT_PDT_ON_SOLD);
    }

    @Test
    @Transactional
    public void createProductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products with an existing ID
        products.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductsMockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(products)))
            .andExpect(status().isBadRequest());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        // Get all the productsList
        restProductsMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(products.getId().intValue())))
            .andExpect(jsonPath("$.[*].pdtName").value(hasItem(DEFAULT_PDT_NAME)))
            .andExpect(jsonPath("$.[*].pdtSubtitle").value(hasItem(DEFAULT_PDT_SUBTITLE)))
            .andExpect(jsonPath("$.[*].pdtDescription").value(hasItem(DEFAULT_PDT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].pdtMaterial").value(hasItem(DEFAULT_PDT_MATERIAL)))
            .andExpect(jsonPath("$.[*].pdtPrice").value(hasItem(DEFAULT_PDT_PRICE)))
            .andExpect(jsonPath("$.[*].pdtStock").value(hasItem(DEFAULT_PDT_STOCK)))
            .andExpect(jsonPath("$.[*].pdtOnSold").value(hasItem(DEFAULT_PDT_ON_SOLD.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", products.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(products.getId().intValue()))
            .andExpect(jsonPath("$.pdtName").value(DEFAULT_PDT_NAME))
            .andExpect(jsonPath("$.pdtSubtitle").value(DEFAULT_PDT_SUBTITLE))
            .andExpect(jsonPath("$.pdtDescription").value(DEFAULT_PDT_DESCRIPTION))
            .andExpect(jsonPath("$.pdtMaterial").value(DEFAULT_PDT_MATERIAL))
            .andExpect(jsonPath("$.pdtPrice").value(DEFAULT_PDT_PRICE))
            .andExpect(jsonPath("$.pdtStock").value(DEFAULT_PDT_STOCK))
            .andExpect(jsonPath("$.pdtOnSold").value(DEFAULT_PDT_ON_SOLD.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProducts() throws Exception {
        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Update the products
        Products updatedProducts = productsRepository.findById(products.getId()).get();
        // Disconnect from session so that the updates on updatedProducts are not directly saved in db
        em.detach(updatedProducts);
        updatedProducts
            .pdtName(UPDATED_PDT_NAME)
            .pdtSubtitle(UPDATED_PDT_SUBTITLE)
            .pdtDescription(UPDATED_PDT_DESCRIPTION)
            .pdtMaterial(UPDATED_PDT_MATERIAL)
            .pdtPrice(UPDATED_PDT_PRICE)
            .pdtStock(UPDATED_PDT_STOCK)
            .pdtOnSold(UPDATED_PDT_ON_SOLD);

        restProductsMockMvc.perform(put("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProducts)))
            .andExpect(status().isOk());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getPdtName()).isEqualTo(UPDATED_PDT_NAME);
        assertThat(testProducts.getPdtSubtitle()).isEqualTo(UPDATED_PDT_SUBTITLE);
        assertThat(testProducts.getPdtDescription()).isEqualTo(UPDATED_PDT_DESCRIPTION);
        assertThat(testProducts.getPdtMaterial()).isEqualTo(UPDATED_PDT_MATERIAL);
        assertThat(testProducts.getPdtPrice()).isEqualTo(UPDATED_PDT_PRICE);
        assertThat(testProducts.getPdtStock()).isEqualTo(UPDATED_PDT_STOCK);
        assertThat(testProducts.isPdtOnSold()).isEqualTo(UPDATED_PDT_ON_SOLD);
    }

    @Test
    @Transactional
    public void updateNonExistingProducts() throws Exception {
        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductsMockMvc.perform(put("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(products)))
            .andExpect(status().isBadRequest());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        int databaseSizeBeforeDelete = productsRepository.findAll().size();

        // Delete the products
        restProductsMockMvc.perform(delete("/api/products/{id}", products.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
