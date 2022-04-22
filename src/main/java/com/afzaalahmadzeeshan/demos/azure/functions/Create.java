package com.afzaalahmadzeeshan.demos.azure.functions;

import com.afzaalahmadzeeshan.demos.azure.models.Product;
import com.google.gson.Gson;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TableOutput;

import java.util.Optional;

/**
 * POST /create
 */
public class Create {
    /**
     * This function create a new records in the Azure Storage Tables.
     */
    @FunctionName("create")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.POST },
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @TableOutput(name="products", tableName="products") OutputBinding<Product> product,
            final ExecutionContext context) {
        // Update should be in the body
        Gson gson = new Gson();
        final Product productBody = gson.fromJson(request.getBody().orElse("null"), Product.class);

        // push the updated value
        product.setValue(productBody);

        if (productBody == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass the product information in the request body").build();
        } else {
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body("Updated, fetch the object again.")
                    .build();
        }
    }
}
