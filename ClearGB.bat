@echo off
echo.
echo.
echo.
echo 取消文件、文件夹属性
echo.
echo.
attrib -R -A -S -H * /S /D
echo.
echo 删除垃圾文件
echo.
del /F /S  /Q *.ncb
del /F /S  /Q *.user
del /F /S  /Q *.suo
del /F /S  /Q *.scc
del /F /S  /Q *.aps
del /F /S  /Q *.vspscc
del /F /S  /Q *.~*

echo.
echo.
echo 删除 .svn 文件夹
set beginDir="."   
for /f "tokens=* delims=" %%i in ('dir /ad /b/s %beginDir% ^| findstr /i "\\\.svn$"') do (   
 rmdir /S /Q "%%i"  
 echo %%i  
)
echo.
echo.
echo 删除 Debug 文件夹
set beginDir="."  
for /f "tokens=* delims=" %%i in ('dir /ad /b/s %beginDir% ^| findstr /i "\\\Debug$"') do (   
 rmdir /S /Q "%%i"  
 echo %%i  
)

echo.
echo.
echo 删除 Release 文件夹
for /f "tokens=* delims=" %%i in ('dir /ad /b/s %beginDir% ^| findstr /i "\\\Release$"') do (   
 rmdir /S /Q "%%i"  
 echo %%i  
)

echo.
echo 完成
pause