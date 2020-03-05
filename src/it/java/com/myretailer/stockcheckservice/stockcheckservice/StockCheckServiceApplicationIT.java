package com.myretailer.stockcheckservice.stockcheckservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockCheckServiceApplicationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void createProductBadJson() throws Exception {
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content("adsfd")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    @Order(2)
    void createProduct() throws Exception {
        String productRequest = "{\"unitPrice\":50,\"name\":\"test\"}";
        mockMvc.perform(
                post("/product").contentType(MediaType.APPLICATION_JSON).content(productRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(
                        document("product", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    @Test
    @Order(3)
    void updateInventoryCurrentAndMnimumStockLevel() throws Exception {
        String inventoryRequest = "{\"productId\":4,\"currentStockLevel\":50,\"minimumStockLevel\":100," +
                "\"blocked\":false}";
        mockMvc.perform(
                put("/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(inventoryRequest))
                .andExpect(status().isOk())
                .andDo(
                        document("inventory", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    @Test
    @Order(4)
    void updateInventoryMarkProductAsBlocked() throws Exception {
        String inventoryBlockRequest = "{\"productId\":4,\"blocked\":true}";
        mockMvc.perform(
                put("/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(inventoryBlockRequest))
                .andExpect(status().isOk())
                .andDo(
                        document("inventory", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }

    @Test
    @Order(5)
    void stockCheck() throws Exception {
        String productRequest = "{\"unitPrice\":10,\"name\":\"product_one\"}";
        String productRequestOneOffOrder = "{\"unitPrice\":100,\"name\":\"product_two\"}";
        String inventoryRequest = "{\"productId\":5,\"currentStockLevel\":75,\"minimumStockLevel\":150," +
                "\"blocked\":false}";
        String inventoryRequestOneOffOrder = "{\"productId\":6,\"currentStockLevel\":75,\"minimumStockLevel\":65," +
                "\"oneOffOrder\":true, \"oneOffOrderQuantity\":25}";
        String stockAuditRequest = "{\"productIds\":[4,5,6]}";
        createProduct(productRequest);
        createProduct(productRequestOneOffOrder);

        updateInventory(inventoryRequest);
        updateInventory(inventoryRequestOneOffOrder);

        mockMvc.perform(
                post("/stock-audit").contentType(MediaType.APPLICATION_JSON).content(stockAuditRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productsToReorder").isArray())
                .andExpect(jsonPath("$.productsToReorder", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.productsToReorder", Matchers.hasItem(5)))
                .andExpect(jsonPath("$.doNotReorder").isArray())
                .andExpect(jsonPath("$.doNotReorder", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.doNotReorder", Matchers.hasItem(4)))
                .andExpect(jsonPath("$.productsForOneOffOrder").isArray())
                .andExpect(jsonPath("$.productsForOneOffOrder", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.productsForOneOffOrder", Matchers.hasItem(6)))
                .andDo(
                        document("stock-audit", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));

    }

    private void updateInventory(String inventoryRequest) throws Exception {
        mockMvc.perform(
                put("/inventory").contentType(MediaType.APPLICATION_JSON).content(inventoryRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void createProduct(String productRequest) throws Exception {
        mockMvc.perform(
                post("/product").contentType(MediaType.APPLICATION_JSON).content(productRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}