package com.example.counterapp

import com.example.counterapp.api.LetterCounterController
import com.example.counterapp.api.WordCounterApi
import com.example.counterapp.domain.WordDto
import org.springframework.http.HttpStatus
import spock.lang.Specification


class LetterCounterControllerTest extends Specification {

    def wordCounterApi = Mock(WordCounterApi)
    def controller = new LetterCounterController(wordCounterApi)


    def 'should count letters for specific words'() {
        given:
        wordCounterApi.lettersCounter(request) >> expectedResponse

        when:
        def response = controller.wordCounter(request)

        then:
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == expectedResponse

        where:
        request                  | expectedResponse
        [countThis: 'as']             | new WordDto(countedLetters: [['a', 1], ['s', 1]])
        [countThis: 'Massachusetts']  | new WordDto(countedLetters: [['M', 1], ['a', 2], ['s', 4], ['c', 1], ['h', 1], ['u', 1], ['e', 1], ['t', 2]])
        [countThis: 'municipalities'] | new WordDto(countedLetters: [['m', 1], ['u', 1], ['n', 1], ['i', 4], ['c', 1], ['p', 1], ['a', 1], ['l', 1], ['t', 1], ['e', 1], ['s', 1]])
        [countThis: 'Customizations'] | new WordDto(countedLetters: [['C', 1], ['u', 1], ['s', 2], ['t', 2], ['o', 2], ['m', 1], ['i', 2], ['z', 1], ['a', 1], ['n', 1]])

    }

    def 'should thrown an error when invalid request'() {
        when:
        def response = controller.wordCounter(invalidRequest)
        
        then:
        response.getStatusCode() == HttpStatus.BAD_REQUEST
        
        where:
        invalidRequest << [[wor2d: 'as'], [countThis: 'a%s'], ['': 'as']]
    }
}
