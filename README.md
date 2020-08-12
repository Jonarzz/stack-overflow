# Question

https://stackoverflow.com/questions/63382047/unable-to-wire-in-dependency-using-mockbean-in-webmvctest

# Answer

Since you need to call a mocked method (`getInstance()`) during Spring context initialization (inside the `Controller`'s constructor), you need to mock the said method in a different way. The mocked bean has to be not only provided as an existing object, but also it should have it's mocked behavior defined.

Addtionally, `IProcessor` implementations are not configured as Spring beans, so Spring will not inject them - `ProcessorFactory` calls `new` explicitly and creates the objects without Spring involvement.

I've created a simple project to reproduce your problem and provide a solution - you can find it [here on GitHub][1] if you want to check if the whole thing is working, but here's the most important test snippet (I've simplified the methods a bit):

    @WebMvcTest(Controller.class)
    class ControllerTest {
    
        private static final IProcessor PROCESSOR = mock(IProcessor.class);
    
        @TestConfiguration
        static class InnerConfiguration {
    
            @Bean
            ProcessorFactory processorFactory() {
                ProcessorFactory processorFactory = mock(ProcessorFactory.class);
                when(processorFactory.getInstance())
                        .thenReturn(PROCESSOR);
                return processorFactory;
            }
    
        }
    
        @Autowired
        private MockMvc mockMvc;
    
        @Test
        void test1() throws Exception {
            String result = "this is a test";
            when(PROCESSOR.process(any()))
                    .thenReturn(result);
    
            mockMvc.perform(post("/test/test")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{}"))
                   .andDo(print())
                   .andExpect(status().is2xxSuccessful())
                   .andExpect(content().string(result));
        }
    
    }


  [1]: https://github.com/Jonarzz/stack-overflow/tree/63382047