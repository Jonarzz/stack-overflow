package io.github.jonarzz;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;
import java.util.concurrent.atomic.*;

class SomeServiceTest {

    SomeService someService = new SomeService();

    @Test
    void doStuff() {
        var listConstruction = mockCreationOnce(ArrayList.class);

        someService.doStuff();

        var mockedList = listConstruction.constructed()
                                         .get(0);
        var inOrder = Mockito.inOrder(mockedList);
        inOrder.verify(mockedList)
               .add(1);
        inOrder.verify(mockedList)
               .add(2);
        inOrder.verify(mockedList)
               .add(3);
        Mockito.verifyNoMoreInteractions(mockedList);
    }

    static <T> MockedConstruction<T> mockCreationOnce(Class<T> clazz) {
        // to operate on the MockedConstruction within a lambda passed to its creation
        // we have to be able to pass the object to the lambda
        // and to do that it needs to be final => AtomicReference usage
        // (it could also be a custom class with a field set at a later time;
        // the reference passed to the lambda has to be final, its contents may change)
        var constructionHolder = new AtomicReference<MockedConstruction<T>>();
        var listConstruction = Mockito.mockConstruction(
                clazz,
                context -> {
                    try {
                        return Mockito.withSettings();
                    } finally {
                        // we're closing the MockedConstruction after first call
                        // from within the doStuff() method
                        // to avoid the construction being mocked in Mockito internal calls
                        var construction = constructionHolder.get();
                        // we're using closeOnDemand() instead of close() to avoid exceptions here
                        // (no-op if closed already)
                        construction.closeOnDemand();
                    }
                }
        );
        // here we're setting the MockedConstruction in the AtomicReference
        // it happens before the lambda call,
        // which will be invoked for the first (and last) time
        // during ArrayList constructor call in the doStuff() method
        constructionHolder.set(listConstruction);
        return listConstruction;
    }
}