# Realismo (mod para Minecraft 1.20.1 - Fabric)

Hace el juego mas realista con mecanicas de supervivencia:

- **Heridas:** con 3 corazones o menos cojeas; con 1.5 corazones o menos tambien te cuesta minar.
- **Peso:** llevar el inventario muy lleno te vuelve lento.
- **Clima:** el frio extremo, el calor del desierto y la lluvia te desgastan mas rapido. Una pechera de cuero te protege.
- **Esfuerzo:** correr consume mas hambre.

No afecta a jugadores en modo creativo o espectador.

## Como obtener el .jar (elige UNA opcion)

### Opcion A: en tu PC (Windows)

1. Instala Java 17 (JDK) y Gradle 8.5 o superior.
2. Haz doble clic en `build.bat`.
3. Tu mod queda en `build\libs\realismo-1.0.0.jar`.

### Opcion B: sin instalar nada (con GitHub, gratis)

1. Crea una cuenta en github.com y un repositorio nuevo.
2. Sube todo el contenido de esta carpeta (incluida `.github`).
3. Entra en la pestana **Actions**, abre **Compilar mod** y pulsa **Run workflow**.
4. Cuando termine (unos minutos), descarga `realismo-mod` desde la seccion **Artifacts**. Dentro esta el `.jar`.

### Opcion C: comando manual

Con Java 17 y Gradle instalados, abre una terminal en esta carpeta y ejecuta: `gradle build`

1. Abre una terminal dentro de la carpeta `realismo-mod`.
2. Ejecuta: `gradle build`
3. El archivo del mod queda en `build/libs/realismo-1.0.0.jar` (no uses el que termina en `-sources`).

## Como instalarlo en TLauncher

1. En TLauncher elige la version **Fabric 1.20.1** (en el selector de versiones aparece como "Fabric") y ejecuta el juego una vez.
2. Descarga **Fabric API** para 1.20.1 desde Modrinth o CurseForge y ponlo en la carpeta `mods`.
3. Pon `realismo-1.0.0.jar` en la misma carpeta `mods`
   (Windows: `%appdata%\.minecraft\mods`).
4. Abre el juego con el perfil Fabric 1.20.1.

## Para que ademas se VEA realista

Instala tambien **Sodium + Iris** (mods) y un shader como **Complementary Reimagined** o **BSL** en la carpeta `shaderpacks`, mas un resource pack realista.

## Ajustes

Todos los valores (umbrales de vida, peso, cansancio) estan en
`src/main/java/com/realismo/RealismoMod.java`. Cambia los numeros y vuelve a compilar.
