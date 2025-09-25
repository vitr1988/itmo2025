package ru.itmo.jpa.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.jpa.config.AppProperties;
import ru.itmo.jpa.dto.GroupDto;
import ru.itmo.jpa.service.GroupService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    private final AppProperties appProperties;
    private final HttpServletRequest httpRequest;
    private final HttpSession httpSession;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(method = RequestMethod.POST)
    public void createGroup(@RequestBody @Valid GroupDto groupDto, HttpServletResponse httpResponse) {
        String param1 = httpRequest.getParameter("param1");
        groupService.createGroup(groupDto);
    }

    @PutMapping("/{id}")
    public void updateGroup(@PathVariable Long id, @RequestBody @Valid GroupDto groupDto) {
        groupService.updateGroup(id, groupDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        return groupService.deleteGroup(id) && "VALUE".equals(appProperties.getCriticalValue())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<GroupDto> findAllGroups() {
        List<GroupDto> allGroups = groupService.findAllGroups();
        httpSession.setAttribute("groups", allGroups);
        return allGroups;
    }

    @GetMapping("/{id}")
    public Optional<GroupDto> findById(@PathVariable Long id) {
        return groupService.findById(id);
    }
}
