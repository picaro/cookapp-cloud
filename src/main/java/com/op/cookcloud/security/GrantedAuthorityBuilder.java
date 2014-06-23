package com.op.cookcloud.security;

import com.op.cookcloud.model.base.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.Collection;
import java.util.HashSet;

public class GrantedAuthorityBuilder {

    private final Collection<GrantedAuthority> auths;

    public static GrantedAuthorityBuilder create(Person user) {
        return new GrantedAuthorityBuilder(user);
    }

    private GrantedAuthorityBuilder(Person user) {
        //this.user = user;
        auths = new HashSet<GrantedAuthority>();
        auths.add(new GrantedAuthorityImpl("ROLE_USER"));
    }

    public Collection<GrantedAuthority> buildAuthoritiesList() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(auths);
        authorities.add(new GrantedAuthorityImpl("ROLE_SUPPORT_USER"));
        return authorities;
    }

}
