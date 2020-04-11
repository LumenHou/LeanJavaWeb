啥也没学, 讲太深了, 明天换视频看

@Configuration 标记是一个配置类
通过new AnnotationConfigApplicationContext(ConfigClassTest.class)的方式传入此类, 并拿到容器

@ComponentScan(value = "com.lean.lumen", useDefaultFilters = false,includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
指定要开启扫描的包, 并将所有扫描到的加入至容器中
includeFilters = Filter[] //需要禁用默认扫描 useDefaultFilters = false
excludeFilters = Filter[]

@bean 给容器中放入一个bean 使用方法名来定义为bean的id