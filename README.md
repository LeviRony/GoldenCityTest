## Opening Trace Viewer
You can open a saved trace using either the Playwright CLI or in the browser at trace.playwright.dev. Make sure to add the full path to where your trace.zip file is located.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
```