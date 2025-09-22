package ru.itmo.jpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.jpa.dto.GroupDto;
import ru.itmo.jpa.service.GroupService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(method = RequestMethod.POST)
    public void createGroup(@RequestBody @Valid GroupDto groupDto) {
        groupService.createGroup(groupDto);
    }

    @PutMapping("/{id}")
    public void updateGroup(@PathVariable Long id, @RequestBody @Valid GroupDto groupDto) {
        groupService.updateGroup(id, groupDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping
    public List<GroupDto> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("/{id}")
    public Optional<GroupDto> findById(@PathVariable Long id) {
        return groupService.findById(id);
    }
}
