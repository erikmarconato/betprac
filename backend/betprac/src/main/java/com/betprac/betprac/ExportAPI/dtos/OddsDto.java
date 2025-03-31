package com.betprac.betprac.ExportAPI.dtos;

public record OddsDto(
        String betType,

        String value,

        Double odd
) {
}
