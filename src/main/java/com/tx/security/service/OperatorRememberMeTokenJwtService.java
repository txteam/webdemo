/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月14日
 * <修改描述:>
 */
package com.tx.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * 操作人员Token(for rememer-me)Utils.
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorRememberMeTokenJwtService {
    
    /** jwt签名 */
    private String signingKey = "secret";
    
    /**
     * 提取用户名<br/>
     * <功能详细描述>
     * @param token
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    /**
     * 提取到期时间<br/>
     * <功能详细描述>
     * @param token
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * 验证token<br/>
     * <功能详细描述>
     * @param token
     * @param userDetails
     * @return [参数说明]
     * 
     * @return Boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean validateToken(String token, String username) {
        final String usernameOfToken = extractUsername(token);
        return (username.equals(usernameOfToken) && !isExpired(token));
    }
    
    /**
     * 是否到期<br/>
     * <功能详细描述>
     * @param token
     * @return [参数说明]
     * 
     * @return Boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private boolean isExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    /**
     * 生成token<br/>
     * <功能详细描述>
     * @param username
     * @param claims
     * @param validitySeconds
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String generateToken(String username, Map<String, Object> claims,
            int validitySeconds) {
        String token = createToken(username, claims, validitySeconds);
        return token;
    }
    
    /**
     * 从token中根据传入的方法提取信息<br/>
     * <功能详细描述>
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public <T> T extractClaim(String token,
            Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * 提取所有的Claims信息<br/>
     * <功能详细描述>
     * @param token
     * @return [参数说明]
     * 
     * @return Claims [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private Claims extractAllClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.signingKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException
                | MalformedJwtException | SignatureException
                | IllegalArgumentException e) {
            claims = new DefaultClaims();
        }
        return claims;
    }
    
    /**
     * 创建token<br/>
     * <功能详细描述>
     * @param subject
     * @param claims
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String createToken(String subject, Map<String, Object> claims,
            int validitySeconds) {
        validitySeconds = validitySeconds <= 0 ? 1000 * 60 * 60 * 24
                : validitySeconds;
        return Jwts.builder()
                .setClaims(claims == null ? new HashMap<>() : claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + validitySeconds))
                .signWith(SignatureAlgorithm.HS256, this.signingKey)
                .compact();
    }
    
    /**
     * @param 对signingKey进行赋值
     */
    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }
    
}
