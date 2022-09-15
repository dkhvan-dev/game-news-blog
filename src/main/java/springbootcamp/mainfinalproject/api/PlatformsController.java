package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.service.GamePlatformService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformsController {
    private final GamePlatformService gamePlatformService;

    @GetMapping()
    public ResponseEntity<List<GamePlatform>> getAllPlatforms() {
        return new ResponseEntity<>(gamePlatformService.getAllPlatforms(), HttpStatus.OK);
    }

    @GetMapping("{platformId}")
    public ResponseEntity<GamePlatform> getPlatformById(@PathVariable(name = "platformId") Long platformId) {
        return new ResponseEntity<>(gamePlatformService.getPlatformById(platformId), HttpStatus.OK);
    }
}
