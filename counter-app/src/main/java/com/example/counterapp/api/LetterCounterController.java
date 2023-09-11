package com.example.counterapp.api;

import static com.example.counterapp.domain.RequestValidator.REQUEST_KEY;
import static com.example.counterapp.domain.RequestValidator.validateRequest;

import com.example.counterapp.domain.WordDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/counter")
@RequiredArgsConstructor
public class LetterCounterController {

    private final WordCounterApi wordCounterApi;

    //It can be done e.g. by passing single string.
    @PostMapping("/count-word")
    public ResponseEntity<WordDto> wordCounter(@Valid @RequestBody @NotNull Map<String, String> request) {
        //It can be optimized, using @ExceptionHandler.
        if (!validateRequest(request)) {
            log.warn("Request payload is invalid, %s".formatted(request.get(REQUEST_KEY)));
            return ResponseEntity.badRequest().build();
        }
        log.trace("Processing request");
        var letters = wordCounterApi.lettersCounter(request);
        return ResponseEntity.ok(letters);
    }
}

