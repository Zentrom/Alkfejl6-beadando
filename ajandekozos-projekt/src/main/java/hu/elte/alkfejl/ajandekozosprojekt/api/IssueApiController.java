package hu.elte.alkfejl.ajandekozosprojekt.api;

import hu.elte.alkfejl.ajandekozosprojekt.model.Issue;
import hu.elte.alkfejl.ajandekozosprojekt.service.IssueService;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin.Role.ADMIN;
import static hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin.Role.USER;

@RestController
@RequestMapping("/api/issues")
public class IssueApiController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Role({ADMIN, USER})
    @GetMapping
    private ResponseEntity<Iterable<Issue>> list() {
        Iterable<Issue> issues = issueService.listByRole(userService.getUserLogin());
        return ResponseEntity.ok(issues);
    }

    @Role({ADMIN, USER})
    @PostMapping
    private ResponseEntity<Issue> create(@RequestBody Issue issue) {
        Issue saved = issueService.create(issue);
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER})
    @GetMapping("/{id}")
    private ResponseEntity<Issue> read(@RequestParam int id) {
        Issue read = issueService.read(id);
        return ResponseEntity.ok(read);
    }

    @Role(ADMIN)
    @PutMapping("/{id}")
    private ResponseEntity<Issue> update(@RequestParam int id, @RequestBody Issue issue) {
        Issue updated = issueService.update(id, issue);
        return ResponseEntity.ok(updated);
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@RequestParam int id) {
        issueService.delete(id);
        return ResponseEntity.ok().build();
    }
}