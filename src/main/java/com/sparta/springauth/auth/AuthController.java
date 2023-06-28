package com.sparta.springauth.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping("/create-cookie") // 쿠키를 저장하는 메소드
    public String createCookie(HttpServletResponse res) {
        addCookie("Robbie Auth", res);

        return "createCookie";
    }

    @GetMapping("/get-cookie") // 쿠키 가져오기
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
                                        // 가지고 오고 싶은 쿠키 이름을 넣어주면 됨
        System.out.println("value = " + value);

        return "getCookie : " + value;
    }

    @GetMapping("/create-session")
    public String createSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 새로운 세션을 생성한 후 반환
        HttpSession session = req.getSession(true);

        // 세션에 저장될 정보 Name - Value 를 추가합니다.
        session.setAttribute(AUTHORIZATION_HEADER, "Robbie Auth");

        return "createSession";
    }


    @GetMapping("/get-session")
    public String getSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 null 반환
        HttpSession session = req.getSession(false);

        String value = (String) session.getAttribute(AUTHORIZATION_HEADER); // 가져온 세션에 저장된 Value 를 Name 을 사용하여 가져옵니다.
                        // 반환 값이 Object이기 때문에 String으로 반환
        System.out.println("value = " + value);

        return "getSession : " + value;
    }

    public static void addCookie(String cookieValue, HttpServletResponse res) { // 쿠키가 생성되는 과정
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행
                                        // 첫 번째 : encode 할 값, 타입)
            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}