package com.ecomtesting.hooks;

import com.ecomtesting.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
