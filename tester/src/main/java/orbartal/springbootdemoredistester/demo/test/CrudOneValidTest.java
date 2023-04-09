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
public class CrudOneValidTest {

	private static final String UUID_1 = UUID.randomUUID().toString();
	private static final String VALUE_1 = "va1";
	private static final String VALUE_2 = "va2";

	private final Gson gson = new Gson();

	private final DemoUrlProvider urlProvider = new DemoUrlProvider();

	@Order(0)
	@Test
	public void test0DeleteAllDemo() throws Exception {
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
	public void test1GetAllDemoEmptyBeforeCreate() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertEquals("[]", response.body());
	}

	@Order(2)
	@Test
    public void test2CreateDemo() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(UUID_1, VALUE_1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
		Assertions.assertNotNull(response.body());
    }

	@Order(3)
	@Test
    public void test3GetAllDemosAfterCreate() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		@SuppressWarnings("rawtypes")
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(1, output.size());
		@SuppressWarnings("rawtypes")
		Map FirstResult =  (Map) output.get(0);
		Assertions.assertEquals(UUID_1, FirstResult.get("uuid"));
		Assertions.assertEquals(VALUE_1, FirstResult.get("value"));
    }
	
	@Order(4)
	@Test
    public void test4UpdateDemo() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(UUID_1, VALUE_2);
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
	@Order(5)
	@Test
    public void test5GetAllDemosAfterUpdate() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(1, output.size());
		Map FirstResult =  (Map) output.get(0);
		Assertions.assertEquals(UUID_1, FirstResult.get("uuid"));
		Assertions.assertEquals(VALUE_2, FirstResult.get("value"));
    }


	@Order(6)
	@Test
	 public void test6DeleteDemo() throws Exception {
		String url = urlProvider.buildUrlDemoDelete(UUID_1);
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
    }

	@Order(7)
	@Test
	public void test7GetAllDemoEmptyAfterDelete() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertEquals("[]", response.body());
	}


}