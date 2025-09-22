package ru.itmo.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.jpa.dto.GroupDto;
import ru.itmo.jpa.mapper.GroupMapper;
import ru.itmo.jpa.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    @Transactional
    public void createGroup(GroupDto dto) {
        groupRepository.save(groupMapper.toEntity(dto));
    }

    @Transactional
    public void updateGroup(Long id, GroupDto dto) {
        groupRepository.findById(id).ifPresent(
                group -> {
                    group.setName(dto.getName());
                    group.setNameEn(dto.getNameEn());
                }
        );
    }

    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<GroupDto> findAllGroups() {
        return groupMapper.toDto(groupRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<GroupDto> findById(Long id) {
        return groupRepository.findById(id).map(groupMapper::toDto);
    }
}
