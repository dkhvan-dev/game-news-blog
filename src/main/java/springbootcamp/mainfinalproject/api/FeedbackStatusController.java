package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.FeedbackStatus;
import springbootcamp.mainfinalproject.service.FeedbackStatusService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/feedbackStatus")
@RequiredArgsConstructor
public class FeedbackStatusController {
    private final FeedbackStatusService feedbackStatusService;

    @GetMapping()
    public ResponseEntity<List<FeedbackStatus>> getAllFeedbackStatus() {
        return new ResponseEntity<>(feedbackStatusService.getAllFeedbackStatus(), HttpStatus.OK);
    }

    @GetMapping("{feedbackStatusId}")
    public ResponseEntity<FeedbackStatus> getFeedbackStatusById(@PathVariable(name = "feedbackStatusId") Long feedbackStatusId) {
        return new ResponseEntity<>(feedbackStatusService.getFeedbackStatusById(feedbackStatusId), HttpStatus.OK);
    }
}
