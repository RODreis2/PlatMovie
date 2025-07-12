package com.PlatMovie.Mapper;

import com.PlatMovie.controller.request.StreamingRequest;
import com.PlatMovie.controller.response.StreamingResponse;
import com.PlatMovie.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest Request){
        return Streaming
                .builder()
                .Name(Request.name())
                .build();
    }
    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
