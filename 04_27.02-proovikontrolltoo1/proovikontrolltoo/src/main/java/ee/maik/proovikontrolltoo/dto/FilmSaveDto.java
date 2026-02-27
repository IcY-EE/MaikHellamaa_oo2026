package ee.maik.proovikontrolltoo.dto;

import ee.maik.proovikontrolltoo.entity.FilmType;

public record FilmSaveDto(String title, FilmType type) {}