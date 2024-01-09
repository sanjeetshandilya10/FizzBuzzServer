package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import spark.Spark;

public class FizzBuzzRestServer {
	
	private static Map<String, Integer> requestCounts = new HashMap<>();
	private static String mostUsedRequest = "";
	private static int maxRequestCount = 0;
	
	public static void main(String[] args) {
		Spark.port(8080);
		
		Spark.post("/process", (req, res) ->{
			int int1 = Integer.parseInt(req.queryParams("int1"));
			int int2 = Integer.parseInt(req.queryParams("int2"));
			int limit = Integer.parseInt(req.queryParams("limit"));
			String str1 = req.queryParams("str1");
			String str2 = req.queryParams("str2");
			
			List<String> resultList = processNumbers(int1, int2, limit, str1, str2);
			updateRequestStats(req.pathInfo());
			
			return resultList;
		});
		
		Spark.get("/most_used_request", (req, res) -> {
			return new MostUsedRequestInfo(mostUsedRequest, maxRequestCount);
		});
	}
	
	private static List<String> processNumbers(int int1, int int2, int limit, String str1, String str2){
		return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> {
                    if (i % (int1 * int2) == 0) {
                        return str1 + str2;
                    } else if (i % int1 == 0) {
                        return str1;
                    } else if (i % int2 == 0) {
                        return str2;
                    } else {
                        return String.valueOf(i);
                    }
                })
                .collect(Collectors.toList());
	}
	
	private static void updateRequestStats(String path) {
		requestCounts.put(path,  requestCounts.getOrDefault(path, 0) + 1);
		
		for(Map.Entry<String, Integer> entry: requestCounts.entrySet()) {
			if(entry.getValue() > maxRequestCount) {
				maxRequestCount = entry.getValue();
				mostUsedRequest = entry.getKey();
			}
		}
	}
	
	static class MostUsedRequestInfo{
		private String mostUsedRequest;
		private int hitCount;
		
		public MostUsedRequestInfo(String mostUsedRequest, int hitCount) {
			this.mostUsedRequest = mostUsedRequest;
			this.hitCount = hitCount;
		}
		
		public void setMostUsedRequest(String mostUsedRequest) {
			this.mostUsedRequest = mostUsedRequest;
		}
		public String getMostUsedRequest() {
			return this.mostUsedRequest;
		}
		
		public void setHitCount(int hitCount) {
			this.hitCount = hitCount;
		}
		public int getHitCount() {
			return this.hitCount;
		}
	}
	
}
