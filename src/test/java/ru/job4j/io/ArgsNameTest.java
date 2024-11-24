package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenKeyNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("This key: 'Xms' is missing");
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Arguments not passed to program");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-=?msg=Exit=' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainKeyThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-=?msg=Hello="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-=?msg=Hello=' does not contain a key");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-request=' does not contain a value");
    }

    @Test
    void whenStringDoesNotContainValueThenExceptionThrown2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-encoding="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-encoding=' does not contain a value");
    }

    @Test
    void whenThereNoEqualSignThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "-request?msgHello"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-request?msgHello' does not contain an equal sign");
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx=512", "request=?msg=Exit="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument 'request=?msg=Exit=' does not start with a '-' character");
    }

    @Test
    void whenKeyIsMissingThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-==password"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-==password' does not contain a key");
    }

    @Test
    void whenNoValueForKeyThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-key=' does not contain a value");
    }

    @Test
    void whenNoEqualSignBetweenKeyAndValueThenExceptionThrown() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key:value"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument '-key:value' does not contain an equal sign");
    }

    @Test
    void whenNoHyphenPrefixThenExceptionThrownForKeyValue() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"key=value"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Error: This argument 'key=value' does not start with a '-' character");
    }
}