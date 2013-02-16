@echo off
if "%OS%" == "Windows_NT" setlocal

if NOT DEFINED JAVA_HOME goto :err
if NOT DEFINED CODEBONE_HOME goto :errCodeboneHome

set ARG=%1
set CODEBONE_SITE=%CODEBONE_HOME%\site
set CODEBONE_TEMPLATE=%CODEBONE_HOME%\template

set CLASSPATH="%CODEBONE_HOME%\bin"
for %%i in ("%CODEBONE_HOME%\lib\*.jar") do call :append "%%i"
set CMD="%JAVA_HOME%\bin\java" -cp %CLASSPATH% -jar %CODEBONE_HOME%/codebone-generator-*.jar
if /i "%ARG%" == "version" goto version
if /i "%ARG%" == "init" goto init
if /i "%ARG%" == "config" goto config
goto codebone

:append
set CLASSPATH=%CLASSPATH%;%1
goto :eof

:init
echo "Initialize project..."
xcopy %CODEBONE_SITE%\* . /S /E /Y /Q
mkdir template
xcopy %CODEBONE_TEMPLATE%\* template /S /E /Y /Q
goto finally

:config
%CMD% DatabaseConfigurationTools -init -path %cd%
goto finally

:codebone
%CMD% GeneratorTools -path %cd% %*
goto finally

:version
%CMD% VersionTools
goto finally

 :err
echo The JAVA_HOME environment variable must be set to run this program!
pause
goto :eof

:errCodeboneHome
echo ERROR: CODEBONE_HOME not found in your enviroment.
echo Please set the CODEBONE_HOME variable in your enviroment to match the location of your CODEBONE installation.
pause
goto :eof

:finally

ENDLOCAL