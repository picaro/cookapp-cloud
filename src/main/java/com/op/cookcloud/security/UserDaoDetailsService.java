package com.op.cookcloud.security;

import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.dao.impl.RoleDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class UserDaoDetailsService implements UserDetailsService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    public UserDaoDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        Person user = personDao.findUserByMail(email);
        Collection<GrantedAuthority> authorities = GrantedAuthorityBuilder.create(user).buildAuthoritiesList();
        return new UserDetailsWithUserInformation(user.getEmail(), user.getPassword(), true, true, true, true, authorities,
                user.getFirstName());
    }

}
