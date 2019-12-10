package cinemacatalog;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ConsumerPactTest {
    @Rule
//    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("inventory_provider", PactSpecVersion.V3, this);
    public PactProviderRuleMk2 mockProvider
            = new PactProviderRuleMk2("test_provider", "localhost", 9000, this);


    @Pact(consumer = "test_consumer", provider = "test_provider")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/pact")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"condition\": true, \"name\": \"tom\"}")
//				.given("test POST")
//				.uponReceiving("POST REQUEST")
//				.method("POST")
//				.headers(headers)
//				.body("{\"name\": \"Michael\"}")
//				.path("/pact")
//				.willRespondWith()
//				.status(201)
                .toPact();
    }

    @Test
    @PactVerification()
    public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {

        // when
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(mockProvider.getUrl() + "/pact", String.class);
        // then
        assertEquals(response.getStatusCode().value(),200);
        assertTrue(response.getHeaders().get("Content-Type").contains("application/json"));
        Assert.assertThat(response.getBody(), CoreMatchers.containsString("condition"));
    }


}
