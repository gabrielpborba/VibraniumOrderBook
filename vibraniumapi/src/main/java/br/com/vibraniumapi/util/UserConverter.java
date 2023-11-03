package br.com.vibraniumapi.util;

import br.com.vibraniumapi.dto.UserDto;
import br.com.vibraniumapi.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserDto convertToDTO(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getName(), entity.getAmount(), entity.getQuantity());
    }

    public static List<UserDto> convertToDTOList(List<UserEntity> entityList) {
        return entityList.stream()
            .map(UserConverter::convertToDTO)
            .collect(Collectors.toList());
    }
}
