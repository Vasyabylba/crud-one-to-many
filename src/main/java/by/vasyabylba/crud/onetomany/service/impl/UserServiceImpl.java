package by.vasyabylba.crud.onetomany.service.impl;

import by.vasyabylba.crud.onetomany.dto.UserRequest;
import by.vasyabylba.crud.onetomany.dto.UserResponse;
import by.vasyabylba.crud.onetomany.exception.ResourceNotFoundException;
import by.vasyabylba.crud.onetomany.mapper.UserMapper;
import by.vasyabylba.crud.onetomany.model.User;
import by.vasyabylba.crud.onetomany.repository.UserRepository;
import by.vasyabylba.crud.onetomany.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<UserResponse> getList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse getOne(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toUserResponse(userOptional.orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(id))));
    }

    @Override
    public List<UserResponse> getMany(List<Long> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse create(UserRequest dto) {
        User user = userMapper.toUser(dto);
        User resultUser = userRepository.save(user);
        return userMapper.toUserResponse(resultUser);
    }

    @Override
    public UserResponse put(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(id)));

        userMapper.updateWithNull(userRequest, user);

        User resultUser = userRepository.save(user);
        return userMapper.toUserResponse(resultUser);
    }

    @Override
    public List<Long> putMany(List<Long> ids, UserRequest userRequest) {
        Collection<User> users = userRepository.findAllById(ids);

        for (User user : users) {
            userMapper.updateWithNull(userRequest, user);
        }

        List<User> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(User::getId)
                .toList();
    }

    @Override
    public UserResponse patch(Long id, JsonNode patchNode) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(id)));

        UserRequest userRequest = userMapper.toUserRequest(user);
        objectMapper.readerForUpdating(userRequest).readValue(patchNode);
        userMapper.updateWithNull(userRequest, user);

        User resultUser = userRepository.save(user);
        return userMapper.toUserResponse(resultUser);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<User> users = userRepository.findAllById(ids);

        for (User user : users) {
            UserRequest userRequest = userMapper.toUserRequest(user);
            objectMapper.readerForUpdating(userRequest).readValue(patchNode);
            userMapper.updateWithNull(userRequest, user);
        }

        List<User> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(User::getId)
                .toList();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteMany(List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
}
