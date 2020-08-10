Without seeing more of your code, it's hard to tell why your test configuration does not meet your needs. I've created a sample project with a few different examples similar to your case and some other - you can find it [here on GitHub][1]. Below is the list of examples you can find there with a short description.

---

    @SpringBootTest(classes = TestedController.class)
    @AutoConfigureMockMvc
    public class TestWithoutConfiguration {
    
        @Test
        void fails() {
    
        }
    
    }

This test fails, since no configuration (apart from the controller bean) is provided - no beans can be found and injected.

---

    @SpringBootTest(classes = {TestedController.class, TestConfiguration.class})
    @AutoConfigureMockMvc
    public class TestWithTestButWithoutMockBeanConfiguration {
    
        @Test
        void fails() {
    
        }
    
    }

Here we use a test configuration that provides a service bean for our controller, but we still lack a bean used by that service - `MockedUtilInterface` (see below).

---

    @SpringBootTest(classes = {TestedController.class, TestConfiguration.class})
    @AutoConfigureMockMvc
    public class TestWithTestConfiguration {
    
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private MockedUtilInterface mockedUtil;
    
        @Test
        void test() throws Exception {
            when(mockedUtil.value())
                    .thenReturn(100);
    
            mockMvc.perform(get("/test"))
                   .andExpect(content().string("100 - rest service from component scan value"));
        }
    
    }

This test passes - TestConfiguration provides the service bean and we're also creating a `MockedUtilInterface` bean as a mock. The mock is injected into the service to which the test configuration points and the service is injected into the actual controller.

---

    @SpringBootTest
    @AutoConfigureMockMvc
    public class TestWithActualConfiguration {
    
        @Autowired
        private MockMvc mockMvc;
    
        @Test
        void test() throws Exception {
            mockMvc.perform(get("/test"))
                   .andExpect(content().string("0 - not a test value"));
        }
    
    }

This test also passes and it uses all the actual configuration, no test configuration is loaded, so all the beans are the beans from our actual application (as if it was run in a container).

---

    @SpringBootTest
    @AutoConfigureMockMvc
    public class TestWithInnerTestConfiguration {
    
        @Configuration
        @Import(TestedApplication.class)
        static class InnerConfiguration {
    
            @Bean
            TestedServiceInterface service(MockedUtilInterface mockedUtil) {
                return () -> mockedUtil.value() + " - inner value";
            }
    
        }
    
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private MockedUtilInterface mockedUtil;
    
        @Test
        void test() throws Exception {
            when(mockedUtil.value())
                    .thenReturn(12345);
    
            mockMvc.perform(get("/test"))
                   .andExpect(content().string("12345 - inner value"));
        }
    
    }

And here's an example of an inner class configuration that overrides some beans, uses some actual beans and can also make use of provided `@MockBeans`.

---

[Here][2] you can find quite a few ways to configure the beans for a test.


  [1]: https://github.com/Jonarzz/stack-overflow/tree/63342469
  [2]: https://reflectoring.io/spring-boot-test/