package com.example.qreventprenotation;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginUnitTest {
    String regex = "^(.+)@(.+)$";

    @Test
    public void valid (){
        String password = "password";
        String email = "user@domain.com";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        assertTrue(((matcher.matches())) && (password.length() >= 8 && password.length() <= 16));
    }

    @Test
    public void notValid1() {
        String password = "marcolino1998";
        String email = "@domain.com";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        assertTrue(((matcher.matches()))  && (password.length() >= 8 && password.length() <= 16));
    }

    @Test
    public void notValid2() {
        String password = "marcolino1998";
        String email = "user#domain.com";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        assertTrue(((matcher.matches()))  && (password.length() >= 8 && password.length() <= 16));
    }

    @Test
    public void notValid3 (){
        String password = "psw";
        String email = "user@domain.com";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        assertTrue(((matcher.matches()))  && (password.length() > 8 && password.length() < 16));
    }

    @Test
    public void notValid4 (){
        String password = "passwordpassword1998";
        String email = "user@domain.com";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        assertTrue(((matcher.matches()))  && (password.length() > 8 && password.length() < 16));
    }
}
