package com.vinastore.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieUtils {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(hours*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }
}
