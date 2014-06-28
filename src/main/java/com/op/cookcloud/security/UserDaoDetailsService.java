package com.op.cookcloud.security;

import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.dao.impl.RoleDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Role;
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

public class UserDaoDetailsService implements UserDetailsService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

  //  private final Collection<GrantedAuthority> auths;

    public UserDaoDetailsService() {
//        auths = new HashSet<GrantedAuthority>();
//        auths.add(new GrantedAuthorityImpl("ROLE_USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        Person user = personDao.findUserByMail(email);
//        List<Role> roles = roleDao.findByPeson(user);
//        user.setRoles(roles);
        Collection<GrantedAuthority> authorities = GrantedAuthorityBuilder.create(user).buildAuthoritiesList();
        return new UserDetailsWithUserInformation(user.getEmail(), user.getPassword(), true, true, true, true, authorities,
                user.getFirstName());
    }

}
