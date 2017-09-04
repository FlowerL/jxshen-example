package com.jxshen.example.mock;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MockTest {

    @Test
    public void doTest() {
        List mock = Mockito.mock(List.class);
        Mockito.when(mock.get(Mockito.anyInt())).thenReturn(1);
        Assert.assertEquals("预期返回1", 1, mock.get(new Random().nextInt()));
    }
}
