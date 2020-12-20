package com.backend.seqaq.config;

import com.backend.seqaq.service.UsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;

/** 自定义身份认证 基于HMAC（ 散列消息认证码）的控制域 */
public class JWTShiroRealm extends AuthorizingRealm {
  private final Logger log = LoggerFactory.getLogger(JWTShiroRealm.class);

  protected UsersService usersService;

  public JWTShiroRealm(UsersService usersService) {
    this.usersService = usersService;
    // 这里使用我们自定义的Matcher
    this.setCredentialsMatcher(new JWTCredentialsMatcher());
  }
  /** 限定这个Realm只支持我们自定义的JWT Token */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JWTToken;
  }

  /**
   * 认证信息.(身份验证) : Authentication 是用来验证用户身份 controller登录一样，也是获取用户的salt值，给到shiro，由shiro来调用matcher来做认证
   * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
      throws AuthenticationException {
    JWTToken jwtToken = (JWTToken) authcToken;
    String token = jwtToken.getToken();

    Users user = usersService.getJwtTokenInfo(JwtUtils.getUsername(token));
    if (user == null) throw new AuthenticationException("token过期，请重新登录");

    return new SimpleAuthenticationInfo(user, user.getSalt(), "jwtRealm");
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    return new SimpleAuthorizationInfo();
  }
}
