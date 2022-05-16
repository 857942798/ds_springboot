package com.ds.springSecurity.config;

import com.ds.springSecurity.filter.CustomizeAbstractSecurityInterceptor;
import com.ds.springSecurity.filter.CustomizeAccessDecisionManager;
import com.ds.springSecurity.filter.CustomizeFilterInvocationSecurityMetadataSource;
import com.ds.springSecurity.filter.MyAuthenticationFilter;
import com.ds.springSecurity.handler.*;
import com.ds.springSecurity.service.impl.CustumUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-09
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private CustomLogoutHandler logoutHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    //实例自定义登录校验接口 【内部有 数据库查询】
    @Autowired
    private CustumUserDetailsService dbUserDetailsService;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private OnAuthenticationHandler onAuthenticationHandler;
//    //访问决策管理器
//    @Autowired
//    CustomizeAccessDecisionManager accessDecisionManager;
//    //实现权限拦截
//    @Autowired
//    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
//    @Autowired
//    CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        filter.setFilterProcessesUrl("/doLogin_new");
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

        /**
         * 忽略过滤的静态文件路径
         */
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers(
//                        "/**/*.js",
//                        "/**/*.css",
//                        "/**/*.html"
//                );
        web.ignoring()
                .antMatchers(
                        "/**"
                );
    }

    /**
     * 全局的跨域配置
     */
    @Bean
    public WebMvcConfigurer WebMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry corsRegistry) {
                //仅仅让/login可以跨域
//                corsRegistry.addMapping("/login").allowCredentials(true).allowedHeaders("*");
                //仅仅让/logout可以跨域
//                corsRegistry.addMapping("/logout").allowCredentials(true).allowedHeaders("*");
                //允许所有接口可以跨域访问
                corsRegistry.addMapping("/**").allowCredentials(true).allowedHeaders("*");
            }
        };

    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//        http.authorizeRequests().
//                //antMatchers("/getUser").hasAuthority("query_user").
//                //antMatchers("/**").fullyAuthenticated().
//                        withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setAccessDecisionManager(accessDecisionManager);//决策管理器
//                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
//                        return o;
//                    }
//                }).
//                //登出
//                        and().logout().
//                permitAll().//允许所有用户
//                logoutSuccessHandler(logoutHandler).//登出成功处理逻辑
//                deleteCookies("JSESSIONID").//登出之后删除cookie
//                //登入
//                        and().formLogin().
//                permitAll().//允许所有用户
//                successHandler(loginSuccessHandler).//登录成功处理逻辑
//                failureHandler(loginFailureHandler).//登录失败处理逻辑
//                //异常处理(权限拒绝、登录失效等)
//                        and().exceptionHandling().
//                accessDeniedHandler(customAccessDeniedHandler).//权限拒绝处理逻辑
//                authenticationEntryPoint(new Http403ForbiddenEntryPoint()).//匿名用户访问无权限资源时的异常处理
//                //会话管理
//                        and().sessionManagement().
//                maximumSessions(1).//同一账号同时登录最大用户数
//                expiredSessionStrategy(sessionInformationExpiredStrategy);//会话失效(账号被挤下线)处理逻辑
//                http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
//    }


    //拦截规则设置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启授权认证
        //开启跨域共享，关闭同源策略【允许跨域】
        http.cors()
                //跨域伪造请求=无效，
                .and().csrf().disable();
        //配置自定义路径拦截规则
        http.authorizeRequests()
//        只要有 "user","admin"任意最少一个权限即可访问路径"/user/**"的所有接口
//                .antMatchers("/user/**").hasAnyAuthority("ROLE_user", "ROLE_admin")
//                //只有权限"admin"才可以访问"/admin/**"所有路径 和 接口 "/vip"
//                .antMatchers("/admin/**", "/vip").hasAuthority("ROLE_admin")
                //所有请求都需要验证,必须要放在antMatchers路径拦截之后，不然拦截失效
//                .anyRequest().authenticated();
        // //路径拦截权限的名称必须与权限列表注册的一样，经过测试，方法级别的注解权限需要ROLE_前缀 ，因此，
        // 路径拦截权限的名称、注解权限名称、数据库存储的权限名称都要加ROLE_前缀最好，避免出现错误，
        // 如果数据库的权限名称不加ROLE_前缀，那么在注册权限列表的时候记得拼接ROLE_前缀
//                .antMatchers(HttpMethod.POST, "/api/data").hasAuthority("add")
//                .antMatchers(HttpMethod.GET, "/v1/page_user1").hasAuthority("query")
//                .antMatchers("/home").hasAuthority("base");
                .antMatchers("/login").permitAll()
                .anyRequest().access("@rbacServiceImpl.hasPermission(request,authentication)");


        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //登录配置
//        http.formLogin()
//                //登录名参数
//                .usernameParameter("username")
//                //密码参数
//                .passwordParameter("password")
//                //post登录访问路径
//                .loginProcessingUrl("/login").loginPage("/tyhmeleaf/info");
        http.formLogin()
                //登录名参数
                .usernameParameter("username")
                //密码参数
                .passwordParameter("password")
                //post登录访问路径
                .loginPage("/tyhmeleaf/info");
//
//
//        //登录结果处理
//        http.formLogin()
//                //登录成功
//                .successHandler(loginSuccessHandler)
//                //登录失败
//                .failureHandler(loginFailureHandler);

        //登录退出处理
        http.logout()
                //post登出访问路径
                .logoutUrl("/logout")
                //成功退出处理
                .logoutSuccessHandler(logoutHandler)
                //清除认证信息
                .clearAuthentication(true).permitAll();

        //异常抛出处理
//        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())     //-- 403
//                //访问拒绝处理【无权】
//                .accessDeniedHandler(customAccessDeniedHandler);

        http.exceptionHandling()
                //访问拒绝处理【无权】
                .accessDeniedHandler(customAccessDeniedHandler);
        //开启cookie自动登录
        http.rememberMe()
                //自动登录成功处理//todo
//                .authenticationSuccessHandler(new  ....)
                //密钥
//                .key("unique-and-secret")
                //cookie名   `
//                .rememberMeCookieName("remember-me-cookie-name")
                .rememberMeParameter("remember")
                //生命周期，单位毫秒
                .tokenValiditySeconds(24 * 60 * 60);

        // session并发管理 ，原理是其缺省实现是将session记录在内存map中，因此不能用于集群环境中，服务器1中记录的信息和服务器2记录的信息并不相同；
        //
        // Session的并发控制,这里设为最多一个，只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线
        http.sessionManagement()
                .maximumSessions(1)
                    .expiredSessionStrategy(sessionInformationExpiredStrategy);//会话过期策略
        //当一个用户已经认证过了，在另外一个地方重新进行登录认证，spring security可以阻止其再次登录认证，从而保持原来的会话可用性
        //存在一个问题，当用户登陆后，没有退出直接关闭浏览器，则再次打开浏览器时，此时浏览器的session若被删除的话，用户只能等到服务器的session过期后，才能再次登录。
//        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        //默认是开通session fixation防护的.因此可以不写,防护的原理为，每当用户认证过后，就会重新生成一个新的session，并抛弃旧的session
//        http.sessionManagement().sessionFixation().migrateSession();


        //解决不允许显示在iframe的问题
//        http.headers().frameOptions().disable();
    }

    /**
     * 添加 UserDetailsService， 实现自定义登录校验，数据库查询
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        //注入用户信息，每次登录都会来这查询一次信息，因此不建议每次都向mysql查询，应该使用redis
//        builder.userDetailsService(dbUserDetailsService)
//                //密码加密方式
//                .passwordEncoder(passwordEncoder());
        builder.userDetailsService(dbUserDetailsService).passwordEncoder(customPasswordEncoder);
    }

    /**
     * BCryptPasswordEncoder相关知识：
     * 用户表的密码通常使用MD5等不可逆算法加密后存储，为防止彩虹表破解更会先使用一个特定的字符串（如域名）加密，然后再使用一个随机的salt（盐值）加密。
     * 特定字符串是程序代码中固定的，salt是每个密码单独随机，一般给用户表加一个字段单独存储，比较麻烦。
     * BCrypt算法将salt随机并混入最终加密后的密码，验证时也无需单独提供之前的salt，从而无需单独处理salt问题。
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

