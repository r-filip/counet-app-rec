package com.example.counterapp.domain;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;

@UtilityClass
public class RequestValidator {

    public static final String REQUEST_KEY = "countThis";
    private static final String REGEX = "([a-zA-Z]*)";

    public static boolean validateRequest(Map<String, String> request) {
        return request.entrySet().stream()
            .allMatch(RequestValidator::isValid);
    }

    private static boolean isValid(Map.Entry<String, String> entry) {
        return isEmptyOrBlank(entry)
            && isMatchPattern(entry)
            && entry.getKey().equals(REQUEST_KEY);
    }

    private static boolean isMatchPattern(Map.Entry<String, String> entry) {
        return entry.getKey().matches(REGEX)
            && entry.getValue().matches(REGEX);
    }

    private static boolean isEmptyOrBlank(Map.Entry<String, String> entry) {
        var key = entry.getKey();
        var value = entry.getValue();
        return StringUtils.isNotBlank(key)
            && StringUtils.isNotBlank(value)
            && StringUtils.isNotEmpty(key)
            && StringUtils.isNotEmpty(value);
    }
}
