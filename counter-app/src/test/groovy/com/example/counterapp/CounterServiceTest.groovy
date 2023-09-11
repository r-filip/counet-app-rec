package com.example.counterapp

import com.example.counterapp.domain.CounterService
import spock.lang.Specification

class CounterServiceTest extends Specification{

    def service = new CounterService()
    
    
    def 'should count letter for given word'(){
        given:
        def word =[countThis: 'zzzXXXaaaa']
        
        when:
        def response = service.lettersCounter(word)
        
        then:
        response.getCountedLetters() == [['z', 3], ['X', 3], ['a', 4]]
    }
}
