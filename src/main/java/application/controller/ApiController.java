package application.controller;

import application.entity.client.CiafoImages;
import application.repository.client.CiafoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final CiafoRepository ciafoRepository;

    @GetMapping("/api/come-in-and-find-out/{category}")
    List<CiafoImages> ciafo(@PathVariable String category, @RequestParam int offset) {
        return ciafoRepository.getImagesByCategory(category, offset);
    }
}