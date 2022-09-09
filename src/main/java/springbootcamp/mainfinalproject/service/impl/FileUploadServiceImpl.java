package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.service.FileUploadService;
import springbootcamp.mainfinalproject.service.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final UserService userService;

    @Value("${targetURL}")
    private String targetURL;

    @Override
    public boolean uploadGameImage(MultipartFile multipartFile, Game game) {
        try {
            User currentUser = userService.getCurrentUser();
            String gameImageToken = DigestUtils.sha1Hex(currentUser.getUserId() + "_" + game.getGameName() + "_" + Math.random());
            String gameImage = gameImageToken + "" + multipartFile.getContentType();
            byte[] bytes = gameImage.getBytes();
            Path path = Paths.get(targetURL + "/", gameImage);
            Files.write(path, bytes);
            game.setGameImage(gameImageToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
