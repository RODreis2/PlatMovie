package com.PlatMovie.controller;


import com.PlatMovie.Mapper.StreamingMapper;
import com.PlatMovie.controller.request.StreamingRequest;
import com.PlatMovie.controller.response.StreamingResponse;
import com.PlatMovie.entity.Streaming;
import com.PlatMovie.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/PlatMovie/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        List<StreamingResponse> streamings = streamingService.findAllStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamings);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable long id) {
        return streamingService.findStreamingById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStreamingById(@PathVariable long id) {
        return streamingService.findStreamingById(id)
                .map(streaming -> {
                    streamingService.deleteStreamingById(id);
                    return ResponseEntity.ok("O ID " + streaming.getId() + "  foi deletado com sucesso.");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
