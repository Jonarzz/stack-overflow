# Question
https://stackoverflow.com/questions/64511818/mockito-spy-object-is-not-being-recognized

# Answer
The main source of the problem is lack of injection of the mocked `targetModelObjectFactory` object in the test.
When you're mocking/spying on the class `TargetModelObjectFactory`, the object you get is in no way passed to the tested class,
thus it's reference is null, hence `NullPointerException` is thrown when trying to call the method `create` on an actually null reference.

Depending on the rest of your tested class (I can only guess) you can choose two approaches. The first one is constructor injection
(usually preferable, you can read more [here](https://reflectoring.io/constructor-injection/)):

    class SourceToTargetMapper {
    
        private TargetModelObjectFactory targetModelObjectFactory;
    
        SourceToTargetMapper(TargetModelObjectFactory targetModelObjectFactory) {
            this.targetModelObjectFactory = targetModelObjectFactory;
        }
        
    }
    
The second one is field injection and is possible thanks to annotations like `@Inject`, `@Autowired` etc., depending on the used tool:

    class SourceToTargetMapper {
    
        @Autowired
        private TargetModelObjectFactory targetModelObjectFactory;
    
    }

Both cases can be easily handled using Mockito:

    @Test
    void constructorInjectionTest() {
        TargetModelObjectFactory factory = mock(TargetModelObjectFactory.class);
        SourceToTargetMapper mapper = new SourceToTargetMapper(factory);
        Source source = mock(Source.class);
        Target target = mock(Target.class);
        when(factory.create(Target.class))
                .thenReturn(target);

        Target result = mapper.apply(source);

        assertSame(target, result);
    }

<!-- -->
    
    @Mock
    TargetModelObjectFactory factory;
    @InjectMocks
    private SourceToTargetMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void fieldInjectionTest() {
        Source source = mock(Source.class);
        Target target = mock(Target.class);
        when(factory.create(Target.class))
                .thenReturn(target);

        Target result = mapper.apply(source);

        assertSame(target, result);
    }
    
---
    
Another thing worth noting is the difference between `mock` and `spy` methods from the Mockito library.

When using `mock`, the whole behaviour of the class is handled by Mockito, that's why we call it with a `Class` parameter:

    FirstClass firstObject = mock(FirstClass.class);
    SecondClass secondObject = mock(SecondClass.class);
    
No actual instance of neither `FirstClass` nor `SecondClass` is created.

When using `spy`, we explicitly tell Mockito, which methods should have their behaviour changed and which methods should be actually called
as defined in the class. We can create the spy using a `Class` parameter or an actual object (the latter used more often).
In case of spies, actual behaviour is often not changed at all, since spies can be used to check if the method was actually called:

    MyClass object = spy(new MyClass());
    doStuff(object);
    verify(object, times(1))
        .myMethod();
        
In your case, since you change the behaviour of the `TargetModelObjectFactory` class, probably `mock` will be a better choice.

---

I've created a [repository on GitHub](https://github.com/Jonarzz/stack-overflow/tree/64511818), where you can find all the code - all tests pass.