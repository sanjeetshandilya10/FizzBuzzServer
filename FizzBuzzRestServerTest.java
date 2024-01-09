package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import spark.utils.IOUtils;

public class FizzBuzzRestServerTest {
	
	@BeforeEach
	public void setUp() {
		FizzBuzzRestServer.main(null);
	}
	
	@Test
	public void testProcessNumbersEndpoint() throws IOException {
		
		URL url = new URL("http://localhost:8080/process?int1=2&int2?3&limit=10&str1=Fizz&str2=Buzz");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.connect();
		
		String response =  IOUtils.toString(connection.getInputStream());
		String expectedResponse = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> {
                    if (i % 6 == 0) {
                        return "FizzBuzz";
                    } else if (i % 2 == 0) {
                        return "Fizz";
                    } else if (i % 3 == 0) {
                        return "Buzz";
                    } else {
                        return String.valueOf(i);
                    }
                })
                .collect(Collectors.toList())
                .toString();
		
		assertEquals(expectedResponse, response);
		connection.disconnect();
	}
	
	@Test
	public void testMostUsedRequestEndpoint() throws IOException {
		
		URL url = new URL("http://localhost:8080/most_used_request");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		String response = IOUtils.toString(connection.getInputStream());
		
		connection.disconnect();
	}

}
