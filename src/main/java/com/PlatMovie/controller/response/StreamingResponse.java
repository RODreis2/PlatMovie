package com.PlatMovie.controller.response;

import lombok.Builder;

@Builder
public record StreamingResponse(long id, String name) {
}
