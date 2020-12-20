package com.backend.seqaq.config;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.seqaq.filter.AnyRolesAuthorizationFilter;
import com.backend.seqaq.filter.JwtAuthFilter;
import com.backend.seqaq.service.UsersService;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import java.util.Arrays;
import java.util.Map;

/** shiro配置类 */
@Configuration
public class ShiroConfig {
  @Bean
  public FilterRegistrationBean<Filter> filterRegistrationBean(
      SecurityManager securityManager, UsersService usersService) throws Exception {
    FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
    filterRegistration.setFilter((Filter) shiroFilter(securityManager, usersService).getObject());
    filterRegistration.addInitParameter("targetFilterLifecycle", "true");
    filterRegistration.setAsyncSupported(true);
    filterRegistration.setEnabled(true);
    filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);

    return filterRegistration;
  }

  @Bean
  public Authenticator authenticator(UsersService usersService) {
    ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
    authenticator.setRealms(Arrays.asList(jwtShiroRealm(usersService), dbShiroRealm(usersService)));
    authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
    return authenticator;
  }

  @Bean
  protected SessionStorageEvaluator sessionStorageEvaluator() {
    DefaultWebSessionStorageEvaluator sessionStorageEvaluator =
        new DefaultWebSessionStorageEvaluator();
    sessionStorageEvaluator.setSessionStorageEnabled(false);
    return sessionStorageEvaluator;
  }

  @Bean("dbRealm")
  public Realm dbShiroRealm(UsersService usersService) {
    DbShiroRealm myShiroRealm = new DbShiroRealm(usersService);
    return myShiroRealm;
  }

  @Bean("jwtRealm")
  public Realm jwtShiroRealm(UsersService usersService) {
    JWTShiroRealm myShiroRealm = new JWTShiroRealm(usersService);
    return myShiroRealm;
  }

  /** 设置过滤器 */
  @Bean("shiroFilterFactoryBean")
  public ShiroFilterFactoryBean shiroFilter(
      SecurityManager securityManager, UsersService usersService) {
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    factoryBean.setSecurityManager(securityManager);
    Map<String, Filter> filterMap = factoryBean.getFilters();
    filterMap.put("authcToken", createAuthFilter(usersService));
    filterMap.put("anyRole", createRolesFilter());
    factoryBean.setFilters(filterMap);
    factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

    return factoryBean;
  }

  @Bean
  protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
    DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
    // 用户登录前
    chainDefinition.addPathDefinition("/users/login", "noSessionCreation,anon");
    chainDefinition.addPathDefinition("/users/register", "noSessionCreation,anon");
    chainDefinition.addPathDefinition("/users/active", "noSessionCreation,anon");
    chainDefinition.addPathDefinition("/users/findbyid", "noSessionCreation,anon");
    chainDefinition.addPathDefinition("/users/findbyaccount", "noSessionCreation,anon");
    chainDefinition.addPathDefinition(
        "/query/ques/tag", "noSessionCreation,anon"); // 为了方便调试和之后添加登录前主页，将部分搜索接口设为无需登录即可访问
    chainDefinition.addPathDefinition("/query/ques/title", "noSessionCreation,anon");
    chainDefinition.addPathDefinition("/query/users", "noSessionCreation,anon");

    // 用户登录后
    chainDefinition.addPathDefinition("/users/logout", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition("/image/**", "anon");
    // 限定用户的接口
    chainDefinition.addPathDefinition(
        "/ques/createQues", "noSessionCreation,authcToken[permissive]"); // 问题
    chainDefinition.addPathDefinition("/ques/new", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition("/ques/delQues", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition("/ques/editQues", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/answers/addAnswer", "noSessionCreation,authcToken[permissive]"); // 回答
    chainDefinition.addPathDefinition(
        "/answers/delete", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/answers/editAnswer", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition("/answers/like", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/answers/dislike", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/replies/replyForAnswer", "noSessionCreation,authcToken[permissive]"); // 评论
    chainDefinition.addPathDefinition(
        "/replies/replyForReply", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition("/replies/like", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/replies/dislike", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/followers/followSomeone", "noSessionCreation,authcToken[permissive]"); // 关注用户
    chainDefinition.addPathDefinition(
        "/followers/unfollowSomeone", "noSessionCreation,authcToken[permissive]");
    chainDefinition.addPathDefinition(
        "/UFQ/followSomeQues", "noSessionCreation,authcToken[permissive]"); // 关注问题
    chainDefinition.addPathDefinition(
        "/UFQ/unfollowSomeQues", "noSessionCreation,authcToken[permissive]");
    // 不限定用户的接口
    chainDefinition.addPathDefinition("/ques/findByDid", "noSessionCreation,authcToken"); // 问题
    chainDefinition.addPathDefinition("/ques/findByUid", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition("/answers/findByAid", "noSessionCreation,authcToken"); // 回答
    chainDefinition.addPathDefinition("/answers/findByQid", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition("/answers/findByUid", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition(
        "/replies/findOneReply", "noSessionCreation,authcToken"); // 评论
    chainDefinition.addPathDefinition(
        "/replies/findRepliesForAnswer", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition(
        "/replies/findRepliesForReply", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition("/followers/check", "noSessionCreation,authcToken"); // 关注用户
    chainDefinition.addPathDefinition("/followers/findFans", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition("/followers/findFollowed", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition("/UFQ/findByQid", "noSessionCreation,authcToken"); // 关注问题
    chainDefinition.addPathDefinition("/UFQ/findByUid", "noSessionCreation,authcToken");
    chainDefinition.addPathDefinition("/UFQ/isFollowed", "noSessionCreation,authcToken");

    // 管理员访问控制
    chainDefinition.addPathDefinition(
        "/users/ban", "noSessionCreation,authcToken,anyRole[admin,manager]"); // 封禁用户
    chainDefinition.addPathDefinition(
        "/users/unban", "noSessionCreation,authcToken,anyRole[admin,manager]");
    chainDefinition.addPathDefinition(
        "/ques/banQues", "noSessionCreation,authcToken,anyRole[admin,manager]"); // 封禁问题
    chainDefinition.addPathDefinition(
        "/ques/unbanQues", "noSessionCreation,authcToken,anyRole[admin,manager]");
    chainDefinition.addPathDefinition(
        "/answers/ban", "noSessionCreation,authcToken,anyRole[admin,manager]"); // 封禁回答
    chainDefinition.addPathDefinition(
        "/answers/unban", "noSessionCreation,authcToken,anyRole[admin,manager]");

    chainDefinition.addPathDefinition("/**", "noSessionCreation,authcToken");
    return chainDefinition;
  }

  protected JwtAuthFilter createAuthFilter(UsersService usersService) {
    return new JwtAuthFilter(usersService);
  }

  protected AnyRolesAuthorizationFilter createRolesFilter() {
    return new AnyRolesAuthorizationFilter();
  }
}
