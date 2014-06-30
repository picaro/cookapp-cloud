package com.op.cookcloud.security;

import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

@Slf4j
public class GrantedAuthorityBuilder {

    private final Collection<GrantedAuthority> auths;

    public static GrantedAuthorityBuilder create(Person user) {
        return new GrantedAuthorityBuilder(user);
    }

    private GrantedAuthorityBuilder(Person user) {
        log.debug("GrantedAuthorityBuilder", "start");
        auths = new HashSet<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER".intern()));

        for (Role role : user.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getRole_name().intern()));
        }

    }

    public Collection<GrantedAuthority> buildAuthoritiesList() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(auths);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER".intern()));
        authorities.add(new SimpleGrantedAuthority("ROLE_SUPPORT_USER".intern()));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN".intern()));
        return authorities;
    }

}
