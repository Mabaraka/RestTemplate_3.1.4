package com.example.resttemplate.service;

import com.example.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final String URL = "http://91.241.64.178:7081/api/users";
    private RestTemplate restTemplate;

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAll() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        return responseEntity.getHeaders().get("set-cookie")
                .stream().collect(Collectors.joining(";"));
    }

    public String save(User user, String sessionID) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionID);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    public String update(User user, String sessionID) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionID);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }

    public String delete(Long id, String sessionID) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionID);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        return responseEntity.getBody();
    }

}
