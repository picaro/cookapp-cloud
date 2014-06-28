package com.op.cookcloud.security;

import com.op.cookcloud.dao.impl.RoleDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class GrantedAuthorityBuilder {


    private final Collection<GrantedAuthority> auths;

    public static GrantedAuthorityBuilder create(Person user) {
        return new GrantedAuthorityBuilder(user);
    }

    private GrantedAuthorityBuilder(Person user) {
        //this.user = user;
        auths = new HashSet<GrantedAuthority>();
//        auths.add(new SimpleGrantedAuthority("ROLE_USER"));

        for(Role role:user.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
//        if (user.getEmail().equals("test@test.com")){
//            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
    }

    public Collection<GrantedAuthority> buildAuthoritiesList() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(auths);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_SUPPORT_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

}
