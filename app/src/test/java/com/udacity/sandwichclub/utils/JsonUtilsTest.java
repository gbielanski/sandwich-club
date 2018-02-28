package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;

public class JsonUtilsTest {

    private static final String DUMMY_NAME = "DUMMY_NAME";
    private static final String DUMMY_VALUE = "DUMMY_VALUE";

    @Test
    public void parseSandwichJson_emptyString_returnNotNullObject() {
        //given
        //when
        Sandwich sandwich = JsonUtils.parseSandwichJson(any(String.class));
        //then
        assertThat(sandwich).isNotNull();

    }

}