package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.GamePlatform;

import java.util.List;

public interface GamePlatformService {
    List<GamePlatform> getAllPlatforms();
    GamePlatform getPlatformById(Long platformId);
    GamePlatform addPlatform(GamePlatform gamePlatform);
    GamePlatform editPlatform(GamePlatform gamePlatform);
    void deletePlatform(Long platformId);
}
