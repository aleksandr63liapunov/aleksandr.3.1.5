package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication=context.getBean("communication",Communication.class);
        ResponseEntity<List<User>> responseEntity=communication.getResponseEntity();
        List<User> allUsers= communication.allUsers(responseEntity);
        String cookie= communication.getCookie(responseEntity.getHeaders());
        System.out.println(allUsers);
        User u=new User(3L,"James","Brown", (byte) 3);
        String result=communication.addUser(u,cookie);
        u.setName("Thomas");
        u.setLastName("Shelby");
        result+=communication.upUser(u,cookie);
        result+=communication.deleteUser(3L,cookie);
        System.out.println(result);
        System.out.println(result.length());
        System.out.println(cookie);

    }
}
