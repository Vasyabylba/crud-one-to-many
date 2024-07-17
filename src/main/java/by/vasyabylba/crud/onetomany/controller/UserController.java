package by.vasyabylba.crud.onetomany.controller;

import by.vasyabylba.crud.onetomany.dto.UserRequest;
import by.vasyabylba.crud.onetomany.dto.UserResponse;
import by.vasyabylba.crud.onetomany.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getList() {
        return userService.getList();
    }

    @GetMapping("/{id}")
    public UserResponse getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<UserResponse> getMany(@RequestParam("id") List<Long> ids) {
        return userService.getMany(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest dto) {
        return userService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponse patch(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.put(id, userRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Long> patchMany(@RequestParam("id") List<Long> ids, @RequestBody UserRequest userRequest) {
        return userService.putMany(ids, userRequest);
    }

    @PatchMapping("/{id}")
    public UserResponse patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return userService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam("id") List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return userService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMany(@RequestParam("id") List<Long> ids) {
        userService.deleteMany(ids);
    }
}
