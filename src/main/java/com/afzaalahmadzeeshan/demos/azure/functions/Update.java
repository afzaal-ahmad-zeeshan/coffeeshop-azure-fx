package com.afzaalahmadzeeshan.demos.azure.functions;

import com.afzaalahmadzeeshan.demos.azure.models.Product;
import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableServiceClient;
import com.azure.data.tables.TableServiceClientBuilder;
import com.google.gson.Gson;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TableOutput;

import java.util.Optional;

/**
 * PUT /update
 */
public class Update {
    /**
     * This function updates the records in the Azure Storage Tables.
     */
    @FunctionName("update")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = { HttpMethod.PUT },
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        // Update should be in the body
        Gson gson = new Gson();
        final Product productBody = gson.fromJson(request.getBody().orElse("null"), Product.class);

        // Create the connection
        TableServiceClient client = new TableServiceClientBuilder()
                .connectionString(System.getenv("AzureWebJobsStorage"))  // Read the connection string from env.
                .buildClient();

        TableClient table = client.getTableClient("products");

        if (productBody == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Please pass the product information in the request body")
                    .build();
        } else {
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body("Updated, fetch the object again.")
                    .build();
        }
    }
}
