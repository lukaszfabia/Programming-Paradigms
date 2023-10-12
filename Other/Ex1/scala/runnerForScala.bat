@echo off
setlocal

for %%F in ("%~dp0.") do set "SCRIPT_DIR=%%~fF"

cd /d "%SCRIPT_DIR%"

cls

scala

endlocal
