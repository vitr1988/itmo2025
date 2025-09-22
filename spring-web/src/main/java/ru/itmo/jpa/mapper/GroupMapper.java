package ru.itmo.jpa.mapper;

import org.mapstruct.Mapper;
import ru.itmo.jpa.dto.GroupDto;
import ru.itmo.jpa.model.Group;

import java.util.List;

@Mapper
public interface GroupMapper {
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
    List<GroupDto> toDto(List<Group> group);
    GroupDto toDto(Group group);

    Group toEntity(GroupDto group);
}
