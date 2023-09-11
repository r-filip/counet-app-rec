package com.example.counterapp.domain;

import static com.example.counterapp.domain.RequestValidator.REQUEST_KEY;

import com.example.counterapp.api.WordCounterApi;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class CounterService implements WordCounterApi {


    @Override
    public WordDto lettersCounter(Map<String,String> request) {
        var word = request.get(REQUEST_KEY);
        return countCharacters(word);
    }

    private WordDto countCharacters(String str) {
        var charCount = str.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(
                Function.identity(), 
                LinkedHashMap::new, 
                Collectors.counting())
            );
        var response = new WordDto();
        charCount.forEach((key, value) -> response.getCountedLetters().add(Arrays.asList(key, value)));
        return response;
    }
}
