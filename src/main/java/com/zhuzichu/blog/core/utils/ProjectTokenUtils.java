package com.zhuzichu.blog.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectTokenUtils {
    public static String SECRET = "WETHIS123456";

    /**
     * 生成token
     *
     * @param uid      用户id
     * @param username 用户姓名
     * @return
     * @throws Exception
     */
    public static String createJWTToken(int uid, String username) throws Exception {
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 24);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("uid", uid)
                .withClaim("username", username)
                .withExpiresAt(expiresDate)
                .withIssuedAt(iatDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Map<String, Claim> verifyJWTToken(String token) throws Exception {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已经过期，请重新登录");
        }
        return jwt.getClaims();
    }
}
