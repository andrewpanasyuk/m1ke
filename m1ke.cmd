@echo off
if %~1==init goto :init
::init

:m1ke
java -classpath m1ke %*

:test
:: print for test
echo m1ke %*
goto :end

:init
echo init on

:end
echo ok
::exit