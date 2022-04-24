package com.rabilmiraliyev.bookstore.security.spring;

import com.rabilmiraliyev.bookstore.security.oauth2.Oauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {


    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

//    @Autowired
//    private Oauth2UserService oauth2UserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService);

    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .cors().and()
        .authorizeRequests()
        .antMatchers("/authentication").permitAll()
        .antMatchers("/user/**").permitAll()
        .antMatchers("/blog/*").permitAll()
        .antMatchers("/like/*").permitAll()
        .antMatchers("/fields").permitAll()
        .antMatchers("/filter*").permitAll()
        .antMatchers("/books/**").permitAll()
        .antMatchers("/productinfo/**").permitAll()
        .antMatchers("/home/**").permitAll()
        .antMatchers("/contact*").permitAll()
        .antMatchers("/subscribe/**").permitAll()
        .antMatchers("/registeremail*").permitAll()
        .antMatchers("/resetrequest*").permitAll()
        .antMatchers("/resetpassword*").permitAll()
        .antMatchers("/admin/**").permitAll()
        .antMatchers("/updateOnlyImageBook**").permitAll()
        .antMatchers("/updateOnlyImageBlog**").permitAll()
        .antMatchers(AUTH_WHITELIST).hasAuthority("ADMIN")
        .anyRequest().authenticated()
//        .and()
//        .oauth2Login()
//        .userInfoEndpoint()
//        .userService(oauth2UserService)
//        .and()
        .and()
        .logout()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().httpStrictTransportSecurity().disable();

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
     PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
