package com.PlatMovie.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "O nome da categoria é obrigatório.") String name) {
}
