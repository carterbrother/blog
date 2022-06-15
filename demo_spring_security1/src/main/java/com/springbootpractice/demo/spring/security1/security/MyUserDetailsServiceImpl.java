package com.springbootpractice.demo.spring.security1.security;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityLoginEntity;
import com.springbootpractice.demo.spring.security1.dao.entity.SecurityLoginRoleEntity;
import com.springbootpractice.demo.spring.security1.dao.entity.SecurityRoleEntity;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityLoginEntityExample;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityLoginRoleEntityExample;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityRoleEntityExample;
import com.springbootpractice.demo.spring.security1.dao.mapper.SecurityLoginEntityMapper;
import com.springbootpractice.demo.spring.security1.dao.mapper.SecurityLoginRoleEntityMapper;
import com.springbootpractice.demo.spring.security1.dao.mapper.SecurityRoleEntityMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 说明：用户和角色对应关系
 * @author carter
 * 创建时间： 2020年02月08日 4:19 下午
 **/
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final SecurityLoginEntityMapper securityLoginEntityMapper;
    private final SecurityLoginRoleEntityMapper securityLoginRoleEntityMapper;
    private final SecurityRoleEntityMapper securityRoleEntityMapper;

    public MyUserDetailsServiceImpl(SecurityLoginEntityMapper securityLoginEntityMapper, SecurityLoginRoleEntityMapper securityLoginRoleEntityMapper, SecurityRoleEntityMapper securityRoleEntityMapper) {
        this.securityLoginEntityMapper = securityLoginEntityMapper;
        this.securityLoginRoleEntityMapper = securityLoginRoleEntityMapper;
        this.securityRoleEntityMapper = securityRoleEntityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final SecurityLoginEntityExample securityLoginEntityExample = new SecurityLoginEntityExample();
        securityLoginEntityExample.createCriteria().andUsernameEqualTo(username);
        final List<SecurityLoginEntity> securityLoginEntityList = securityLoginEntityMapper.selectByExample(securityLoginEntityExample);
        if (CollectionUtils.isEmpty(securityLoginEntityList)) {
            throw new UsernameNotFoundException(String.format("%s不存在", username));
        }

        SecurityLoginEntity securityLoginEntity = securityLoginEntityList.get(0);
        Long loginId = securityLoginEntity.getId();

        SecurityLoginRoleEntityExample securityLoginRoleEntityExample = new SecurityLoginRoleEntityExample();
        securityLoginRoleEntityExample.createCriteria().andLoginIdEqualTo(loginId);
        final List<SecurityLoginRoleEntity> securityLoginRoleEntityList = securityLoginRoleEntityMapper.selectByExample(securityLoginRoleEntityExample);

        if (CollectionUtils.isEmpty(securityLoginRoleEntityList)) {
            return transUserDetails(securityLoginEntity, Collections.emptyList());
        }

        SecurityRoleEntityExample securityRoleEntityExample = new SecurityRoleEntityExample();
        securityRoleEntityExample.createCriteria().andIdIn(securityLoginRoleEntityList.stream().map(SecurityLoginRoleEntity::getRoleId).collect(Collectors.toList()));
        final List<SecurityRoleEntity> securityRoleEntityList = securityRoleEntityMapper.selectByExample(securityRoleEntityExample);

        return transUserDetails(securityLoginEntity, securityRoleEntityList.stream().map(SecurityRoleEntity::getRoleEnglishName).collect(Collectors.toList()));
    }

    private UserDetails transUserDetails(SecurityLoginEntity securityLoginEntity, List<String> roleEnglishList) {
        return new UserDetails() {
            private static final long serialVersionUID = -380560978496026881L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return roleEnglishList.stream().map(item -> (GrantedAuthority) () -> item).collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return securityLoginEntity.getPassword();
            }

            @Override
            public String getUsername() {
                return securityLoginEntity.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return securityLoginEntity.getLockFlag() == 1;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return securityLoginEntity.getPasswordExpireDate().isAfter(LocalDateTime.now());
            }

            @Override
            public boolean isEnabled() {
                return securityLoginEntity.getEnableFlag() == 1;
            }
        };
    }
}
