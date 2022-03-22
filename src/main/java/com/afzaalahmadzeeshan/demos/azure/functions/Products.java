package com.afzaalahmadzeeshan.demos.azure.functions;

import com.afzaalahmadzeeshan.demos.azure.models.Product;
import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TableInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * GET /products
 */
public class Products {
    /**
     * The function to get all the products from the table.
     */
    @FunctionName("products")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.GET },
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @TableInput(name = "products", tableName = "products") com.afzaalahmadzeeshan.demos.azure.models.Product[] products,
            final ExecutionContext context) {
        context.getLogger().info("Reading the products list and returning to the user...");

        Gson gson = new Gson();
        String response = gson.toJson(products, Product[].class);

        return request
                .createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(response)
                .build();
    }
}
