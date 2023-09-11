package com.example.counterapp.domain;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class WordDto {
    
    //I did it according to requirements, but I think Map also can be return.
    List<List<Object>> countedLetters= new ArrayList<>();
}
