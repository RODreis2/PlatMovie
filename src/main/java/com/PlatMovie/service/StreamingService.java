package com.PlatMovie.service;

import com.PlatMovie.entity.Streaming;
import com.PlatMovie.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository repository;

    public List<Streaming> findAllStreamings() {
        return repository.findAll();
    }

    public Streaming saveStreaming(Streaming streaming) {
        return repository.save(streaming);
    }

    public Optional<Streaming> findStreamingById(long id) {
        return repository.findById(id);
    }

    public void deleteStreamingById(long id) {
        repository.deleteById(id);
    }
}
