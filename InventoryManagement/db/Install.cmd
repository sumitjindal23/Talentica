@echo off
chcp 65001

Set MYSQL_UID=root

Set DBNAME=inventoryDB

set /p MYSQL_UID=Your MySQL UID:
::
Set MYSQL_PW=
set /p MYSQL_PW=Password:
::
Set OKTORUN=Y


echo Removing %DBNAME% database if exists..
mysql -u %MYSQL_UID% -p%MYSQL_PW% -e "DROP DATABASE IF EXISTS %DBNAME%"

echo Creating %DBNAME% database..
mysql -u %MYSQL_UID% -p%MYSQL_PW% -e "CREATE DATABASE %DBNAME%"

echo Installing database...
type "%CD%\Inventory_ddl.sql" | mysql -u %MYSQL_UID% -p%MYSQL_PW% -D %DBNAME%

echo Inserting System Seed Data...
type "%CD%\UserDetails_seed.sql" | mysql -u %MYSQL_UID% -p%MYSQL_PW% -D %DBNAME%

pause