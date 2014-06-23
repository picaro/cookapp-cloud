package com.op.cookcloud.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailsWithUserInformation extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private String name;

    public UserDetailsWithUserInformation(String username, String password, boolean enabled, boolean accountNonExpired,
                                          boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String name) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
