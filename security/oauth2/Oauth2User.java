package com.rabilmiraliyev.bookstore.security.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class Oauth2User implements OAuth2User {

    private Oauth2User oauth2User;

    public Oauth2User(Oauth2User oauth2User) {
        this.oauth2User = oauth2User;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public String getFullName(){
        return oauth2User.getAttribute("name");
    }
}
