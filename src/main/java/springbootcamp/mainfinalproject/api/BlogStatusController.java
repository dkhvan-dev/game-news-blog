package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootcamp.mainfinalproject.model.BlogStatus;
import springbootcamp.mainfinalproject.service.BlogStatusService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/blogStatus")
public class BlogStatusController {
    private final BlogStatusService blogStatusService;

    @GetMapping
    public ResponseEntity<List<BlogStatus>> getBlogStatuses() {
        return new ResponseEntity<>(blogStatusService.getAllStatus(), HttpStatus.OK);
    }
}
