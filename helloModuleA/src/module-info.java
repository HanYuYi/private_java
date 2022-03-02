module helloModuleA {
    requires java.base;                     // 可不写，任何模块都会自动引入java.base
    exports com.hello.a1;                   // 让其他所有模块都能导入
    exports com.hello.a2 to C;              // 只能让C模块导入
    exports com.hello.a3;
}