package cz.petrfilip.graphqlspring.api.dto;


public record TodoDtoIn(Integer id, String title, boolean completed, Integer userId) {}
