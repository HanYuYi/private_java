module helloModuleB {
    requires transitive helloModuleA;      // 让B传递依赖，相当于导入B后就相当与当入了A
}