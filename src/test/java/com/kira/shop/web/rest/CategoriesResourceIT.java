package com.kira.shop.web.rest;

import com.kira.shop.KiraApp;
import com.kira.shop.domain.Categories;
import com.kira.shop.repository.CategoriesRepository;

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
 * Integration tests for the {@link CategoriesResource} REST controller.
 */
@SpringBootTest(classes = KiraApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategoriesResourceIT {

    private static final String DEFAULT_CAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAT_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAT_ORDER = 1;
    private static final Integer UPDATED_CAT_ORDER = 2;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoriesMockMvc;

    private Categories categories;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Categories createEntity(EntityManager em) {
        Categories categories = new Categories()
            .catName(DEFAULT_CAT_NAME)
            .catDescription(DEFAULT_CAT_DESCRIPTION)
            .catOrder(DEFAULT_CAT_ORDER);
        return categories;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Categories createUpdatedEntity(EntityManager em) {
        Categories categories = new Categories()
            .catName(UPDATED_CAT_NAME)
            .catDescription(UPDATED_CAT_DESCRIPTION)
            .catOrder(UPDATED_CAT_ORDER);
        return categories;
    }

    @BeforeEach
    public void initTest() {
        categories = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategories() throws Exception {
        int databaseSizeBeforeCreate = categoriesRepository.findAll().size();
        // Create the Categories
        restCategoriesMockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categories)))
            .andExpect(status().isCreated());

        // Validate the Categories in the database
        List<Categories> categoriesList = categoriesRepository.findAll();
        assertThat(categoriesList).hasSize(databaseSizeBeforeCreate + 1);
        Categories testCategories = categoriesList.get(categoriesList.size() - 1);
        assertThat(testCategories.getCatName()).isEqualTo(DEFAULT_CAT_NAME);
        assertThat(testCategories.getCatDescription()).isEqualTo(DEFAULT_CAT_DESCRIPTION);
        assertThat(testCategories.getCatOrder()).isEqualTo(DEFAULT_CAT_ORDER);
    }

    @Test
    @Transactional
    public void createCategoriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriesRepository.findAll().size();

        // Create the Categories with an existing ID
        categories.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriesMockMvc.perform(post("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categories)))
            .andExpect(status().isBadRequest());

        // Validate the Categories in the database
        List<Categories> categoriesList = categoriesRepository.findAll();
        assertThat(categoriesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategories() throws Exception {
        // Initialize the database
        categoriesRepository.saveAndFlush(categories);

        // Get all the categoriesList
        restCategoriesMockMvc.perform(get("/api/categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categories.getId().intValue())))
            .andExpect(jsonPath("$.[*].catName").value(hasItem(DEFAULT_CAT_NAME)))
            .andExpect(jsonPath("$.[*].catDescription").value(hasItem(DEFAULT_CAT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].catOrder").value(hasItem(DEFAULT_CAT_ORDER)));
    }
    
    @Test
    @Transactional
    public void getCategories() throws Exception {
        // Initialize the database
        categoriesRepository.saveAndFlush(categories);

        // Get the categories
        restCategoriesMockMvc.perform(get("/api/categories/{id}", categories.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categories.getId().intValue()))
            .andExpect(jsonPath("$.catName").value(DEFAULT_CAT_NAME))
            .andExpect(jsonPath("$.catDescription").value(DEFAULT_CAT_DESCRIPTION))
            .andExpect(jsonPath("$.catOrder").value(DEFAULT_CAT_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingCategories() throws Exception {
        // Get the categories
        restCategoriesMockMvc.perform(get("/api/categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategories() throws Exception {
        // Initialize the database
        categoriesRepository.saveAndFlush(categories);

        int databaseSizeBeforeUpdate = categoriesRepository.findAll().size();

        // Update the categories
        Categories updatedCategories = categoriesRepository.findById(categories.getId()).get();
        // Disconnect from session so that the updates on updatedCategories are not directly saved in db
        em.detach(updatedCategories);
        updatedCategories
            .catName(UPDATED_CAT_NAME)
            .catDescription(UPDATED_CAT_DESCRIPTION)
            .catOrder(UPDATED_CAT_ORDER);

        restCategoriesMockMvc.perform(put("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategories)))
            .andExpect(status().isOk());

        // Validate the Categories in the database
        List<Categories> categoriesList = categoriesRepository.findAll();
        assertThat(categoriesList).hasSize(databaseSizeBeforeUpdate);
        Categories testCategories = categoriesList.get(categoriesList.size() - 1);
        assertThat(testCategories.getCatName()).isEqualTo(UPDATED_CAT_NAME);
        assertThat(testCategories.getCatDescription()).isEqualTo(UPDATED_CAT_DESCRIPTION);
        assertThat(testCategories.getCatOrder()).isEqualTo(UPDATED_CAT_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingCategories() throws Exception {
        int databaseSizeBeforeUpdate = categoriesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoriesMockMvc.perform(put("/api/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categories)))
            .andExpect(status().isBadRequest());

        // Validate the Categories in the database
        List<Categories> categoriesList = categoriesRepository.findAll();
        assertThat(categoriesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategories() throws Exception {
        // Initialize the database
        categoriesRepository.saveAndFlush(categories);

        int databaseSizeBeforeDelete = categoriesRepository.findAll().size();

        // Delete the categories
        restCategoriesMockMvc.perform(delete("/api/categories/{id}", categories.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Categories> categoriesList = categoriesRepository.findAll();
        assertThat(categoriesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
