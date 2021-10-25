package com.example.authorizationcodeauthorizationserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.authorizationcodeauthorizationserver.mybatis.entity.User;
import com.example.authorizationcodeauthorizationserver.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*User user = userMapper.findByUsername(username);*/
        User user = userMapper.findById(1);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        System.err.println(JSONObject.toJSONString(user,true));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
