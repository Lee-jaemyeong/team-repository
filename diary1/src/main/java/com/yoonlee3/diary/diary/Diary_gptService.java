package com.yoonlee3.diary.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
@Service
public class Diary_gptService {
	private static final String API_URL = "https://api.openai.com/v1/chat/completions";

	@Value("${openai.api.key}")
	private String API_KEY;

	public String getAIResponse(String userMessage) {
		RestTemplate restTemplate = new RestTemplate();

		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer " + API_KEY);

		// 요청 바디 작성
		Map<String, Object> body = new HashMap<>();
		body.put("model", "gpt-3.5-turbo");
		body.put("store", true);

		List<Map<String, String>> messages = new ArrayList<>();
<<<<<<< HEAD
		messages.add(Map.of("role", "user", "content", userMessage + " 이 일기를 이모지 5개만 사용해서 요약해줘 "));
=======
		messages.add(Map.of("role", "user", "content", userMessage + " 이 일기를 이모지 5개로으로 요약해줘 "));
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
		body.put("messages", messages);

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		// 요청 전송
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, requestEntity, String.class);
<<<<<<< HEAD
		String responseBody = responseEntity.getBody();
		
		 try {
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode root = mapper.readTree(responseBody);
		        String content = root.path("choices").get(0).path("message").path("content").asText();
		        return content.trim(); // 이모지 요약 텍스트만 반환
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "😕 요약 실패";
		    }
=======
		return responseEntity.getBody();
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}
}
