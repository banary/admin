package com.banary.admin.config;

import com.banary.admin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * SPRING SECURITY USER
 */
public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        if(user != null) {
            this.setId(user.getId());
            this.setUserName(user.getUserName());
            this.setPassword(user.getPassword());
        }       
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        Set<SRole> userRoles = this.getSRoles();
//        
//        if(userRoles != null)
//        {
//            for (SRole role : userRoles) {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
//                authorities.add(authority);
//            }
//        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
