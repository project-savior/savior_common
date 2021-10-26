package com.jerry.savior_common.util;

import com.jerry.savior_common.interfaces.TokenExtractor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
     * token 过期时长,单位（s）
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
        JwtBuilder builder = Jwts.builder();
        if (claims != null) {
            builder.setClaims(claims);
        }
        return builder
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, tokenSecretKey)
                .compact();
    }

    /**
     * 获取subject
     *
     * @param token token
     * @return subject
     */
    public String getSubject(String token) {
        try {
            return (String) extractClaims(token, (Function<Claims, Object>) Claims::getSubject);
        } catch (Exception e) {
            log.warn("解析token失败，原因：{}", e.getMessage());
            return null;
        }
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
    public String getTokenFromRequest(HttpServletRequest request) {
        return this.parser.getTokenFromRequest(request);
    }

    /**
     * 从token提取过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date extractExpiration(String token) {
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
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从token提取所有的claims
     *
     * @param token token
     * @return 所有的claims
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(tokenSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getTokenExpire() {
        return tokenExpire;
    }
}
