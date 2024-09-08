package com.hoavd.fbstore.admin.config.security;

import com.hoavd.fbstore.admin.config.AdminConfig;
import com.hoavd.fbstore.common.constants.JWTConstants;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.admin.model.dto.AuthModel;
import com.hoavd.fbstore.user.entity.Admin;
import com.hoavd.fbstore.user.service.AdminService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value(value = "${jwt.issuer}")
  private String jwtIssuer;

  @Value(value = "${jwt.secret}")
  private String jwtSecret;

  @Value(value = "${jwt.expire.time}")
  private int jwtExpireTime;

  @Autowired
  private AdminConfig adminConfig;

  @Autowired
  private AdminService adminService;

  public String generateToken(Admin admin) {
    Instant instant = Instant.now();
    return Jwts.builder().setSubject(admin.getUsername()).setIssuer(jwtIssuer)
      .claim(JWTConstants.CLAIM_USER_ID, admin.getId()).claim("active_mode", adminConfig.getActiveMode())
      .claim("scopes", Collections.singletonList(Enums.Scopes.ACCESS_TOKEN.authority())).setIssuedAt(Date.from(instant))
      .setExpiration(Date.from(instant.plusSeconds(jwtExpireTime))).signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader(JWTConstants.TOKEN_HEADER);
    List<String> pathList = adminConfig.getLstUriUnauthorized();
    List<String> paths = getPath(pathList);
    if (checkPath(req.getRequestURI().toLowerCase(), paths))
      return null;
    if (pathList.contains(req.getRequestURI().toLowerCase()))
      return null;
    if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith(JWTConstants.TOKEN_PREFIX))
      return bearerToken.replace(JWTConstants.TOKEN_PREFIX, "");
    return null;
  }

  private List<String> getPath(List<String> pathList) {
    List<String> path = new ArrayList<>();
    for (String p : pathList)
      if (p.contains("/**"))
        path.add(p.replace("/**", ""));
    return path;
  }

  private boolean checkPath(String uri, List<String> pathList) {
    for (String p : pathList)
      if (uri.contains(p))
        return true;
    return false;
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
      throw new IllegalArgumentException(ResponseMessageConstants.TOKEN_EXPIRED);
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty");
    }
    throw new IllegalArgumentException(ResponseMessageConstants.TOKEN_INVALID);
  }

  public AuthModel getUserByToken(String token) throws Exception {
    try {
      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      AuthModel userTokenModel = new AuthModel();
      userTokenModel.setUserId(claims.get(JWTConstants.CLAIM_USER_ID, Long.class));
      userTokenModel.setUsername(claims.getSubject().toLowerCase());
      userTokenModel.setActiveMode(claims.get("active_mode", String.class));
      return userTokenModel;
    } catch (ExpiredJwtException ex) {
      throw new Exception(ResponseMessageConstants.LOGIN_REFRESH_EXCEPTION);
    }
  }

  public Authentication getAuthentication(AuthModel userTokenModel, HttpServletRequest request) throws Exception {
    // CHECK USER REFRESH TOKEN IS EXPIRED ?
    Admin admin = adminService.getByIdAndUsername(userTokenModel.getUserId(),
      userTokenModel.getUsername().toLowerCase().trim());
    if (admin != null) {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin, null,
        null);
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      return authenticationToken;
    }
    throw new Exception(ResponseMessageConstants.AUTHENTICATION_EXCEPTION);
  }
}
