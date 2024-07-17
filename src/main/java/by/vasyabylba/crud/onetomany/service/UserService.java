package by.vasyabylba.crud.onetomany.service;

import by.vasyabylba.crud.onetomany.dto.UserRequest;
import by.vasyabylba.crud.onetomany.dto.UserResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserResponse> getList();

    UserResponse getOne(Long id);

    List<UserResponse> getMany(List<Long> ids);

    UserResponse create(UserRequest dto);

    UserResponse put(Long id, UserRequest userRequest);

    List<Long> putMany(List<Long> ids, UserRequest userRequest);

    UserResponse patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    void delete(Long id);

    void deleteMany(List<Long> ids);
}
