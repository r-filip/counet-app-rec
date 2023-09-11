package com.example.counterapp.api;

import com.example.counterapp.domain.WordDto;
import java.util.Map;

public interface WordCounterApi {
    
    WordDto lettersCounter(Map<String,String> request);
}
