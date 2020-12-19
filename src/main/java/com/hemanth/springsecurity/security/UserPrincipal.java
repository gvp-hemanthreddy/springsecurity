package com.hemanth.springsecurity.security;

import com.hemanth.springsecurity.model.Role;
import com.hemanth.springsecurity.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private final String userName;
    private final String password;
    private final Set<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> {
                    Set<GrantedAuthority> permissionAuthorities = getPermissions(role);
                    permissionAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
                    return permissionAuthorities;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return new UserPrincipal(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private static Set<GrantedAuthority> getPermissions(Role role) {
        return role.getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName().getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
