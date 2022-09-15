package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;
import springbootcamp.mainfinalproject.service.ApplicationFormBloggerService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/applicationForms")
@RequiredArgsConstructor
public class ApplicationFormBloggerController {
    private final ApplicationFormBloggerService formBloggerService;

    @GetMapping()
    public ResponseEntity<List<ApplicationFormBlogger>> getAllApplicationForms() {
        return new ResponseEntity<>(formBloggerService.getAllApplications(), HttpStatus.OK);
    }

    @GetMapping("{applicationFormId}")
    public ResponseEntity<ApplicationFormBlogger> getApplicationFormById(@PathVariable(name = "applicationFormId") Long applicationFormId) {
        return new ResponseEntity<>(formBloggerService.getApplicationFormById(applicationFormId), HttpStatus.OK);
    }
}
