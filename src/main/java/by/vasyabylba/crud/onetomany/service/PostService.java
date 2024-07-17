package by.vasyabylba.crud.onetomany.service;

import by.vasyabylba.crud.onetomany.dto.PostRequest;
import by.vasyabylba.crud.onetomany.dto.PostResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<PostResponse> getList();

    List<PostResponse> getListByUser(Long userId);

    PostResponse getOne(Long id);

    List<PostResponse> getMany(List<Long> ids);

    PostResponse create(Long userId, PostRequest dto);

    PostResponse put(Long id, PostRequest postRequest);

    List<Long> putMany(List<Long> ids, PostRequest postRequest);

    PostResponse patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    void delete(Long id);

    void deleteMany(List<Long> ids);

    void deleteByUser(Long userId, Long postId);

    void deleteAllByUser(Long userId);
}
