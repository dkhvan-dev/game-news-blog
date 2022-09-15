package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Feedback;
import springbootcamp.mainfinalproject.service.FeedbackService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping()
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return new ResponseEntity<>(feedbackService.getAllFeedback(), HttpStatus.OK);
    }

    @GetMapping("{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable(name = "feedbackId") Long feedbackId) {
        return new ResponseEntity<>(feedbackService.getFeedbackById(feedbackId), HttpStatus.OK);
    }
}
