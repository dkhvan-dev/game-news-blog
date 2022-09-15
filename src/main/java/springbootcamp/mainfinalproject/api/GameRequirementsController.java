package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.GameRequirements;
import springbootcamp.mainfinalproject.service.GameRequirementsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gameRequirements")
@RequiredArgsConstructor
public class GameRequirementsController {
    private final GameRequirementsService gameRequirementsService;

    @GetMapping()
    public ResponseEntity<List<GameRequirements>> getAllGameRequirements() {
        return new ResponseEntity<>(gameRequirementsService.getAllRequirements(), HttpStatus.OK);
    }

    @GetMapping("{gameRequirementId}")
    public ResponseEntity<GameRequirements> getGameRequirementsById(@PathVariable(name = "gameRequirementId") Long gameRequirementId) {
        return new ResponseEntity<>(gameRequirementsService.getRequirementsById(gameRequirementId), HttpStatus.OK);
    }
}
