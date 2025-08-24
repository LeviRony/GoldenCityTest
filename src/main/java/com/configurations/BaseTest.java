package com.configurations;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeTest
    @Parameters("browserName")
    public void launchBrowser(@Optional("chromium") String browserName) {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "edge":
                browser = playwright.chromium()
                        .launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
                break;
            default:
                throw new IllegalArgumentException("Unknown browser: " + browserName);
        }
    }

    @BeforeMethod
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        log.info("Start tracing");
        context.tracing().start(new Tracing.StartOptions()
                .setSnapshots(true)
                .setScreenshots(true)
                .setSources(true));
    }

    @AfterMethod
    public void closeContext(Method method) throws IOException {
        if (context != null) {
            Files.createDirectories(Paths.get("trace-records"));
            String traceName = method.getAnnotation(Test.class).testName();
            if (traceName == null || traceName.isEmpty()) {
                traceName = method.getName();
            }
            log.info("Stop tracing and save zip");
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace-records/" + traceName + ".zip")));
            context.close();
        }
    }

    @BeforeTest
    public void closeBrowser() {
        try {
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        } catch (PlaywrightException e) {
            log.warn("Browser/playwright already closed: " + e.getMessage());
        }
    }
}
