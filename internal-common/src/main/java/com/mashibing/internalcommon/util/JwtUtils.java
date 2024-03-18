package com.mashibing.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mashibing.internalcommon.constant.IdentityConstant;
import com.mashibing.internalcommon.constant.TokenConstants;
import com.mashibing.internalcommon.dto.TokenResult;
import com.sun.javafx.tk.TKClipboard;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 盐
    private static final String SIGN = "CFAmsb!@#$$$%";
    private static final String JWT_KEY_PHONE = "phone";
    private static final String JWT_KEY_IDENTITY = "identity";
    private static final String JWT_TOKEN_TYPE = "tokenType";
    private static final String JWT_TOKEN_TIME = "tokenTime";


    //生产token
    public static String generatorToken(String passengerPhone,String identity,String tokenType){

        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);


        // token过期时间
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,1);
//        Date date = calendar.getTime();

        map.put(JWT_TOKEN_TIME,Calendar.getInstance().getTime().toString());

        JWTCreator.Builder builder = JWT.create();

        // 整合map
        map.forEach(
                (k,v) -> {
                    builder.withClaim(k,v);
                }
        );

        // 整合过期时间(如果redis中已经存有token过期时间，则此处不需要)
//        builder.withExpiresAt(date);

        // 生产token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }
    //解析token

    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    public static TokenResult checkToken(String token){

        TokenResult tokenResult=null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e){

        }
        return tokenResult;
    }

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("name","zhan san");
//        map.put("age","18");
//        String s = generatorToken(map);

        String accessToken = generatorToken("13661761676", IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = generatorToken("13661761676", IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);

        System.out.println("生成后的token："+accessToken);

        TokenResult tokenResult = parseToken(accessToken);

        System.out.println("解析token后的手机号："+ tokenResult.getPhone()+"解析后的身份："+tokenResult.getIdentity());
    }

}
