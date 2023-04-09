package orbartal.springbootdemoredistester.demo.test;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.gson.Gson;

import orbartal.springbootdemoredistester.demo.DemoDto;
import orbartal.springbootdemoredistester.demo.util.DemoDtoFactory;
import orbartal.springbootdemoredistester.demo.util.DemoUrlProvider;

@TestMethodOrder(OrderAnnotation.class)
public class CrudMultiValidTest {

	//Create 3 uuid sorted by alphabet
	private static List<String> UUIDS = IntStream.of(1,2,3).boxed().map(i->UUID.randomUUID().toString()).sorted().toList();
	private static final String UUID_A1 = UUIDS.get(0);
	private static final String VALUE_A1 = "va1";
	private static final String UUID_B1 = UUIDS.get(1);
	private static final String VALUE_B1 = "vb1";
	private static final String VALUE_B2 = "vb2";
	private static final String UUID_C1 = UUIDS.get(2);
	private static final String VALUE_C1 = "vc1";

	private final DemoUrlProvider urlProvider = new DemoUrlProvider();

	private final Gson gson = new Gson();

	@Order(0)
	@Test
	public void test00DeleteAllStart() throws Exception {
		String url = urlProvider.buildUrlDemo();
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
	}

	@Order(1)
	@Test
	public void test01GetAllEmptyStart() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertEquals("[]", response.body());
	}

	@Order(2)
	@Test
    public void test02CreateDemoA() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(UUID_A1, VALUE_A1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }
	
	@Order(3)
	@Test
    public void test03CreateDemoB() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(UUID_B1, VALUE_B1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }
	
	@Order(4)
	@Test
    public void test04CreateDemoC() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(UUID_C1, VALUE_C1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }

	@SuppressWarnings("rawtypes")
	@Order(5)
	@Test
    public void test05GetAllDemosAfterCreate() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(3, output.size());

		Map aOut =  (Map) output.get(0);
		Assertions.assertEquals(UUID_A1, aOut.get("uuid"));
		Assertions.assertEquals(VALUE_A1, aOut.get("value"));
		
		Map bOut =  (Map) output.get(1);
		Assertions.assertEquals(UUID_B1, bOut.get("uuid"));
		Assertions.assertEquals(VALUE_B1, bOut.get("value"));
		
		Map cOut =  (Map) output.get(2);
		Assertions.assertEquals(UUID_C1, cOut.get("uuid"));
		Assertions.assertEquals(VALUE_C1, cOut.get("value"));
    }

	@Order(6)
	@Test
    public void test06UpdateDemoB() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(UUID_B1, VALUE_B2);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
    }

	@SuppressWarnings("rawtypes")
	@Order(7)
	@Test
    public void test07GetAllDemosAfterUpdateB() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(3, output.size());
	
		Map aOut =  (Map) output.get(0);
		Assertions.assertEquals(UUID_A1, aOut.get("uuid"));
		Assertions.assertEquals(VALUE_A1, aOut.get("value"));
		
		Map bOut =  (Map) output.get(1);
		Assertions.assertEquals(UUID_B1, bOut.get("uuid"));
		Assertions.assertEquals(VALUE_B2, bOut.get("value"));
		
		Map cOut =  (Map) output.get(2);
		Assertions.assertEquals(UUID_C1, cOut.get("uuid"));
		Assertions.assertEquals(VALUE_C1, cOut.get("value"));
    }
	
	@SuppressWarnings("rawtypes")
	@Order(8)
	@Test
    public void test08GetDemoBAfterUpdateB() throws Exception {
		String url = urlProvider.buildUrlDemo() + "/" + UUID_B1;
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Map bOut = gson.fromJson(response.body(), Map.class);

		Assertions.assertEquals(UUID_B1, bOut.get("uuid"));
		Assertions.assertEquals(VALUE_B2, bOut.get("value"));
    }


	@Order(9)
	@Test
	 public void test09DeleteDemoB() throws Exception {
		String url = urlProvider.buildUrlDemoDelete(UUID_B1);
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
    }

	@SuppressWarnings("rawtypes")
	@Order(10)
	@Test
	public void test10GetAllDemoEmptyAfterDeleteB() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(2, output.size());
	
		Map aOut =  (Map) output.get(0);
		Assertions.assertEquals(UUID_A1, aOut.get("uuid"));
		Assertions.assertEquals(VALUE_A1, aOut.get("value"));

		Map cOut =  (Map) output.get(1);
		Assertions.assertEquals(UUID_C1, cOut.get("uuid"));
		Assertions.assertEquals(VALUE_C1, cOut.get("value"));
	}
	
	@Order(11)
	@Test
	public void test11DeleteAllDemoEnd() throws Exception {
		String url = urlProvider.buildUrlDemo();
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
	}
	
	@Order(12)
	@Test
	public void test12GetAllDemoEmptyEnd() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertEquals("[]", response.body());
	}

}