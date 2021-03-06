package com.jerry.savior_common.util;

import com.jerry.savior_common.interfaces.TokenExtractor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 22454
 */
@Slf4j
public class TokenHelper {
    /**
     * token 过期时长
     */
    private final Long tokenExpire;

    /**
     * token加密秘钥
     */
    private final String tokenSecretKey;

    /**
     * token解析器
     */
    public TokenExtractor parser;

    public TokenHelper(Long tokenExpire,
                       String tokenSecretKey,
                       TokenExtractor parser) {
        this.tokenExpire = tokenExpire;
        this.tokenSecretKey = tokenSecretKey;
        this.parser = parser;
    }

    /**
     * 创建token
     *
     * @param subject subject
     * @return token
     */
    public String buildToken(String subject) {
        return buildToken(subject, null);
    }

    /**
     * 创建token
     *
     * @param subject subject
     * @param claims  claims
     * @return token
     */
    public String buildToken(String subject, Map<String, Object> claims) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + tokenExpire);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, tokenSecretKey)
                .compact();
    }

    /**
     * token是否过期
     *
     * @param token token
     * @return 是否过期
     */
    public boolean hasExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * token是否过期
     *
     * @param request request
     * @return 是否过期
     */
    public boolean hasExpired(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        return StringUtils.isBlank(token) || hasExpired(token);
    }

    /**
     * 判断request中是否存在token
     *
     * @param request request
     * @return 是否存在
     */
    public boolean existsToken(HttpServletRequest request) {
        return StringUtils.isNotBlank(getTokenFromRequest(request));
    }

    /**
     * 从request获取token
     *
     * @param request request
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        return this.parser.getTokenFromRequest(request);
    }

    /**
     * 从token提取过期时间
     *
     * @param token token
     * @return 过期时间
     */
    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }


    /**
     * 从token提取Claims的某个值
     *
     * @param token          token
     * @param claimsResolver 提取方法
     * @param <T>            数据类型
     * @return claims
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从token提取所有的claims
     *
     * @param token token
     * @return 所有的claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(tokenSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }


}
