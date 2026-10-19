@echo off
echo Compilando el mod Realismo...
where gradle >nul 2>nul
if errorlevel 1 (
  echo No se encontro Gradle. Instalalo desde https://gradle.org/install/ y vuelve a intentar.
  pause
  exit /b 1
)
call gradle build
if errorlevel 1 (
  echo.
  echo Hubo un error al compilar. Revisa que tengas Java 17 instalado.
  pause
  exit /b 1
)
echo.
echo Listo. Tu mod esta en: build\libs\realismo-1.0.0.jar
echo Copialo a la carpeta mods de tu Minecraft.
pause
