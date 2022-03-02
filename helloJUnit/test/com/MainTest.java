package com;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit测试类
 * Fixtrue：@BeforeEach，@AfterEach，@BeforeAll，@AfterAll
 * 异常测试
 * 体检测试
 * 参数化测试
 */
class MainTest {

    Main main = new Main();

    @BeforeEach
    void setUp() {
    }

    /**
     * 普通测试
     */
    @Test
    void testAdd() {
        assertEquals(10, Main.add(5, 2));
    }

    /**
     * 异常测试
     */
    // 条件测试
    @Disabled
    @Test
    void testNegative() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Main.add(0, -1);
            }
        });
    }

    /**
     * 异常测试语法糖
     */
    @Test
    void testNegative1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Main.add(-1, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Main.add(0, 0);
        });
    }

    /**
     * 条件测试，根据windows系统测试
     * EnabledOnOs：启用
     * DisabledOnOs：禁用
     */
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testWindows() {
        assertEquals("C:\\test.ini", main.getConfigFile("test.ini"));
    }

    /**
     * 条件测试，根据linux，macOS系统测试
     */
    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testLinusAndMac() {
        assertEquals("/user/local/test.cfg", main.getConfigFile("test.cfg"));
    }

    // 条件测试，根据环境变量测试
    // @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true")

    /**
     * 参数化测试，单个数据测试
     * @param s
     * 用一组正数据测试
     */
    @ParameterizedTest
    @ValueSource(strings = { "Java", "C++", "Golang", "Rust" })
    void testSayHello(String s) {
        assertEquals("Hello" + s, main.sayHello(s));
    }

    /**
     * 参数化测试，两个及以上个数据测试1
     * @param input
     * @param result
     */
    @ParameterizedTest
    // 用于编写一个同名的静态方法来提供测试参数
    @MethodSource
    void testCapitalize(String input, String result) {
        assertEquals(result, main.capitalize(input));
    }

    static List<Arguments> testCapitalize() {
        return List.of(
                Arguments.arguments("AbB", "Abb"),
                Arguments.arguments("xnsa", "Xnsa"),
                Arguments.arguments("hellO", "Hello")
        );
    }

    /**
     * 参数化测试，两个及以上个数据测试2
     * @param input
     * @param result
     */
    @ParameterizedTest
    // 每一个字符串表示一行参数
    @CsvSource({ "AbB, Abb", "xnsa, Xnsa", "hellO, Hello" })
    void testCapitalize1(String input, String result) {
        assertEquals(result, main.capitalize(input));
    }

    /**
     * 参数化测试，两个及以上个数据测试3
     * @param input
     * @param result
     */
    @ParameterizedTest
    // 每一个字符串表示一行参数
    @CsvFileSource(resources = {"/test-testCapitalize2.csv"})
    void testCapitalize2(String input, String result) {
        assertEquals(result, main.capitalize(input));
    }

}