package com.vinastore.utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionUtils {

    @Autowired
    HttpSession httpSession;

    public void set(String name, Object value) {
        httpSession.setAttribute(name, value);
    }

}
