package com.codesample.demo.mapper;

import com.codesample.demo.domain.UserEntity;
import com.codesample.demo.request.UserPrincipal;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserPrincipal entityToUserPrincipal(UserEntity userEntity);
}
