package org.sam.mines.examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TownApi;
import org.openapitools.client.model.Town;

@Disabled("This is a sample generated client example")
class ApiClientTest {

    @Test
    void shouldCallApiClient() throws ApiException {
        ApiClient apiClient = new ApiClient();
        apiClient.updateBaseUri("http://localhost:8080");

        TownApi townApi = new TownApi(apiClient);

        Town test = townApi.create(new Town().name("test").postCode("12345"));

        townApi.list().stream().map(Town::getId).forEach(System.out::println);


        Town town = townApi.get(test.getId());

        town.setName("modified by api client");

        townApi.update(town);

        townApi.list().stream().map(Town::getName).forEach(System.out::println);

    }
}
