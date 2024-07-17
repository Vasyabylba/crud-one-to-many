package by.vasyabylba.crud.onetomany.mapper;

import by.vasyabylba.crud.onetomany.dto.PostRequest;
import by.vasyabylba.crud.onetomany.dto.PostResponse;
import by.vasyabylba.crud.onetomany.model.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface PostMapper {
    Post toPost(PostRequest postRequest);

    PostRequest toPostRequest(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostRequest postRequest, @MappingTarget Post post);

    Post updateWithNull(PostRequest postResponse, @MappingTarget Post post);

    @Mapping(source = "userId", target = "user.id")
    Post toPost(PostResponse postResponse);

    @Mapping(source = "user.id", target = "userId")
    PostResponse toPostResponse(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostResponse postResponse, @MappingTarget Post post);
}