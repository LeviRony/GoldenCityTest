## Opening Trace Viewer
You can open a saved trace using either the Playwright CLI or in the browser at trace.playwright.dev. Make sure to add the full path to where your trace.zip file is located.
Run it from the main project, and add the name of the .zip file that you want to see
```
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="show-trace trace-records/fileName.zip"
```