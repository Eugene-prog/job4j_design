package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void getAreaReturnsCorrectAreaForCube() {
        Box box = new Box(8, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(216.0);
    }

    @Test
    void getAreaReturnsCorrectAreaForTetrahedron() {
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(62.3538, withPrecision(0.001d));
    }

    @Test
    void isExistReturnsTrue() {
        Box box = new Box(8, 6);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isExistReturnsFalse() {
        Box box = new Box(-1, -1);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void getNumberOfVerticesReturnsCorrectCountForCube() {
        Box box = new Box(8, 6);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8);
    }

    @Test
    void getNumberOfVerticesReturnsCorrectCountForSphere() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0);
    }

}