package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    private final String path = "./data/invalid.properties";

    private void createTestFile(String filename, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        }
    }

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username")).isEqualTo("postgres");
    }

    @Test
    void whenCommentWithoutPair() {
        String path = "./data/comment_without_pair.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.isEmpty()).isTrue();
    }

    @Test
    void whenMissingKeyThenThrowsIllegalArgumentException() throws IOException {
        createTestFile(path, "=value");
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenMissingValueThenThrowsIllegalArgumentException() throws IOException {
        createTestFile(path, "key= ");
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenMissingEqualsThenThrowsIllegalArgumentException() throws IOException {
        createTestFile(path, "keyvalue");
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenEmptyEntryThenThrowsIllegalArgumentException() throws IOException {
        createTestFile(path, "=");
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}