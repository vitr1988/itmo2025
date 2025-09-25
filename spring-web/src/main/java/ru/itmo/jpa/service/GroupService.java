package ru.itmo.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.itmo.jpa.dto.GroupDto;
import ru.itmo.jpa.mapper.GroupMapper;
import ru.itmo.jpa.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final RestTemplate restTemplate;

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
    public boolean deleteGroup(Long id) {
        ResponseEntity<GroupDto> group = restTemplate.getForEntity(
                "http://localhost:8080/api/groups/%d".formatted(id),
                GroupDto.class);
        boolean exist = group.getStatusCode().is2xxSuccessful();
        if (exist) {
            groupRepository.deleteById(id);
        }
        return exist;
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
