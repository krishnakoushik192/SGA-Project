@echo off
setlocal

set MAVEN_VERSION=3.9.9
set MAVEN_HOME_DIR=%~dp0.mvn\wrapper\apache-maven-%MAVEN_VERSION%
set MAVEN_CMD=%MAVEN_HOME_DIR%\bin\mvn.cmd

if not exist "%MAVEN_CMD%" (
  echo Maven %MAVEN_VERSION% was not found locally. Downloading it now...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference = 'Stop'; $root = '%~dp0.mvn\wrapper'; $version = '%MAVEN_VERSION%'; $zip = Join-Path $root ('apache-maven-' + $version + '-bin.zip'); New-Item -ItemType Directory -Force -Path $root | Out-Null; Invoke-WebRequest -Uri ('https://archive.apache.org/dist/maven/maven-3/' + $version + '/binaries/apache-maven-' + $version + '-bin.zip') -OutFile $zip; Expand-Archive -Force -Path $zip -DestinationPath $root; Remove-Item $zip"
  if errorlevel 1 exit /b 1
)

call "%MAVEN_CMD%" %*
