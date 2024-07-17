package by.vasyabylba.crud.onetomany.mapper;

import by.vasyabylba.crud.onetomany.dto.UserRequest;
import by.vasyabylba.crud.onetomany.dto.UserResponse;
import by.vasyabylba.crud.onetomany.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toUser(UserResponse userResponse);

    UserResponse toUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserResponse userResponse, @MappingTarget User user);

    User toUser(UserRequest userRequest);

    UserRequest toUserRequest(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRequest userRequest, @MappingTarget User user);

    User updateWithNull(UserRequest userRequest, @MappingTarget User user);
}