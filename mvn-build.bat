cd /d %~dp0
cmd /c mvn clean eclipse:clean eclipse:eclipse -DdownloadSources -DdownloadJavadocs
pause
