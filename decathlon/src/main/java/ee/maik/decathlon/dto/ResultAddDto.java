package ee.maik.decathlon.dto;

public record ResultAddDto(
        Long athleteId,
        String event,
        double performance
) {}