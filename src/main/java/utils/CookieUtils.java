package utils;


import model.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CookieUtils {
    private static final String ATT_LOGIN_TIME = "ATTRIBUTE_FOR_LOGIN_TIME";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUsername = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        cookieUsername.setMaxAge(24 * 60 * 60);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today).replace(" ", "");
        Cookie cookieLoginTime = new Cookie(ATT_LOGIN_TIME, reportDate);
        cookieLoginTime.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUsername);
        response.addCookie(cookieLoginTime);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getLoginTimeInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_LOGIN_TIME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUsername = new Cookie(ATT_NAME_USER_NAME, null);
        Cookie cookieLoginTime = new Cookie(ATT_LOGIN_TIME, null);
        cookieUsername.setMaxAge(0);
        cookieLoginTime.setMaxAge(0);
        response.addCookie(cookieUsername);
        response.addCookie(cookieLoginTime);
    }
}
