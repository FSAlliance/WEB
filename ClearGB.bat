@echo off
echo.
echo.
echo.
echo ȡ���ļ����ļ�������
echo.
echo.
attrib -R -A -S -H * /S /D
echo.
echo ɾ�������ļ�
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
echo ɾ�� .svn �ļ���
set beginDir="."   
for /f "tokens=* delims=" %%i in ('dir /ad /b/s %beginDir% ^| findstr /i "\\\.svn$"') do (   
 rmdir /S /Q "%%i"  
 echo %%i  
)
echo.
echo.
echo ɾ�� Debug �ļ���
set beginDir="."  
for /f "tokens=* delims=" %%i in ('dir /ad /b/s %beginDir% ^| findstr /i "\\\Debug$"') do (   
 rmdir /S /Q "%%i"  
 echo %%i  
)

echo.
echo.
echo ɾ�� Release �ļ���
for /f "tokens=* delims=" %%i in ('dir /ad /b/s %beginDir% ^| findstr /i "\\\Release$"') do (   
 rmdir /S /Q "%%i"  
 echo %%i  
)

echo.
echo ���
pause