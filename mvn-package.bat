cd /d %~dp0
cmd /c mvn clean package -Dmaven.test.skip=true
pause
