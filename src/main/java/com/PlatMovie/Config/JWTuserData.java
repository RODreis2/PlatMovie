package com.PlatMovie.Config;

import lombok.Builder;

@Builder
public record JWTuserData(long id, String name, String email) {
}
