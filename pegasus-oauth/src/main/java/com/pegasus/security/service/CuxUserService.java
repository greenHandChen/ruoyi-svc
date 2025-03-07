package com.pegasus.security.service;

import com.pegasus.security.dto.CuxUserDetails;
import com.pegasus.security.exception.RoleNotFoundException;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by enHui.Chen on 2018/3/22 0022.
 */
@Component
public class CuxUserService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CuxUserService.class);
    @Autowired
    private SysLoginService loginService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, RoleNotFoundException {
        LoginUser loginUser = loginService.getLoginUser(userName);
        SysUser sysUser = loginUser.getSysUser();
        Set<String> roles = loginUser.getRoles();

        CuxUserDetails customUserDetails = new CuxUserDetails(userName, sysUser.getPassword(), generateUserAuthority(roles));
        customUserDetails.setScope(Collections.singletonList("default"));
        customUserDetails.setRoles(roles);
        customUserDetails.setSysUser(sysUser);
        customUserDetails.setPermissions(loginUser.getPermissions());

        return customUserDetails;
    }

    private List<SimpleGrantedAuthority> generateUserAuthority(Set<String> roles) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        roles.forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role)));
        return simpleGrantedAuthorities;
    }
}
