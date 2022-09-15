package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.ApplicationBloggerStatus;
import springbootcamp.mainfinalproject.service.ApplicationBloggerStatusService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/applicationStatus")
@RequiredArgsConstructor
public class ApplicationBloggerStatusController {
    private final ApplicationBloggerStatusService applicationBloggerStatusService;

    @GetMapping()
    public ResponseEntity<List<ApplicationBloggerStatus>> getAllApplicationStatus() {
        return new ResponseEntity<>(applicationBloggerStatusService.getAllBloggerStatus(), HttpStatus.OK);
    }

    @GetMapping("{applicationStatusId}")
    public ResponseEntity<ApplicationBloggerStatus> getApplicationStatusById(@PathVariable(name = "applicationStatusId") Long applicationStatusId) {
        return new ResponseEntity<>(applicationBloggerStatusService.getApplicationBloggerStatusById(applicationStatusId), HttpStatus.OK);
    }
}
