package com.example.authorizationcodeauthorizationserver.mybatis.mapper;

import com.example.authorizationcodeauthorizationserver.mybatis.entity.Role;
import com.example.authorizationcodeauthorizationserver.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    User findByUsername(final String username);

    List<Role> findRoleByUserId(final Integer userId);

    User findById(final Integer id);
}
