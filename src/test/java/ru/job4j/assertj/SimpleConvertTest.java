package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four");
        assertThat(list).hasSize(4)
                .contains("four")
                .contains("second", Index.atIndex(1))
                .doesNotContain("five")
                .startsWith("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "six");
        assertThat(set).hasSize(6)
                .contains("five")
                .doesNotContain("seven")
                .doesNotContainSequence("first", "second", "three", "four", "five", "six");
    }

    @Test
    void checkSetWithDuplicates() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> setWithDuplicates = simpleConvert.toSet("first", "second", "first", "four", "second");
        assertThat(setWithDuplicates).hasSize(3)
                .containsExactlyInAnyOrder("first", "second", "four");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("0", "1", "2", "3", "4");
        assertThat(map).hasSize(5)
                .containsKeys("0", "1", "2", "3", "4")
                .containsValues(3, 1, 2)
                .doesNotContainKey("6")
                .doesNotContainValue(6)
                .containsEntry("2", 2);
    }

}