package com.afzaalahmadzeeshan.demos.azure.functions;

import com.google.gson.Gson;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * GET /product
 */
public class Product {
    /**
     * The function to get a single product. This function expects a query string value.
     */
    @FunctionName("product")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.GET },
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @BindingName("id") String id,
            @TableInput(name = "products",
                    tableName = "products",
                    filter = "id eq {id}")
                    com.afzaalahmadzeeshan.demos.azure.models.Product[] products,
            final ExecutionContext context) {
        final String queryId = request.getQueryParameters().get("id");
        context.getLogger().info(String.format("Read the product id as: %s", queryId));

        if (products == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("A product ID was not provided in the query string.").build();
        } else {
            Gson gson = new Gson();
            String response = gson.toJson(products, com.afzaalahmadzeeshan.demos.azure.models.Product[].class);

            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(response)
                    .build();
        }
    }
}
