package com.kalix.testing;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class CamelRouterTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new CamelRouter();
    }
    @Test
    public void checkFileExistsInOutputDirectory() throws InterruptedException
    {
        Thread.sleep(5000);
        File file = new File("data/output");
        assertTrue(file.isDirectory());
        assertEquals(2,file.listFiles().length);
    }

}