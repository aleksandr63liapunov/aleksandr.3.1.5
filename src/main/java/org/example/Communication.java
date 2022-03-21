package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public ResponseEntity<List<User>> getResponseEntity(){
        return restTemplate.exchange(URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {
                });
    }

    public List<User> allUsers(ResponseEntity<List<User>> responseEntity) {
        return responseEntity.getBody();
    }
    public String getCookie(HttpHeaders headers){
        String list=headers.get("Set-Cookie").get(0);
        list=list.split("=")[1].split(";")[0];
        return list;
    }

    public User showUser(Long id) {
        User showUs = restTemplate.getForObject(URL + "/" + id, User.class);
        return showUs;
    }

    public String addUser(User user,String cookie) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie","JSESSIONID="+ cookie);
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    public String upUser(User user,String cookie) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie","JSESSIONID="+ cookie);
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id,String cookie) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie","JSESSIONID="+ cookie);
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL+ "/" + id,HttpMethod.DELETE, entity, String.class);
        return responseEntity.getBody();

    }
}
