# Challengue - Recipes App 🍛

Reto técnico para puesto de Android Developer en Yape.

### Descripción:

El diseño de la Aplicación se inspiró en la sección de comida de la aplicación de Yape, esto para demostrar mis habilidades en maquetación y el uso correcto los componentes basados en Material 3 con Jetpack Compose, usando buenas prácticas de desarrollo aplicando patrones de diseño y arquitectura limpia. Así mismo se agregan algunas funcionalidades que impactan positivamente en la experiencia de usuario. 

## Apk Realese

https://drive.google.com/file/d/1vdOK4sOGAd3KRNlRl87iL8CtNPWibqr7/view?usp=sharing


## Screenshots Responsive

Se le dió soporte a los distintos tipos de pantallas para obtener un diseño responsive adaptable, esto generando Types que permitan personalizar el tamaño de las fuentes y dimensiones.

Small               |  Medium                    
:-------------------------:|:-------------------------:
![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711150744/yape/3-portrait_1_2_t4a21d.png)|![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711150803/yape/5-portrait_1_cdfxec.png)

Large
:-------------------------:|
|![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711151094/yape/1-portrait_1_cyvov4.png)

## Otras pantallas
Detalle de receta               |  Mapa origen de receta                    
:-------------------------:|:-------------------------:
![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711158933/yape/6-portrait_1_1_f3otil.png)|![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711158943/yape/Screenshot_1711158450-portrait_1_aopiux.png)


## API Referencia

#### Obtener todas las recetas

```http
  GET http://demo1707878.mockable.io/cooking-recipes
```

| Key | Header     | Description                |
| :-------- | :------- | :------------------------- |
| `x-api-key` | `DrkHAF?P7j8zoCx4ozG!7MHiHk8cD#zs&` | **Requerido**. para oobtener las recetas. |


## Librerías usadas
#### Coil - Renderizar images
https://github.com/coil-kt/coil
#### Dagger Hilt - Inyección de dependencias

https://dagger.dev/hilt/

#### Retrofit 3- Cliente HTTP
https://github.com/square/retrofit/

#### Google Maps compose - Visualizar mapas 
https://github.com/googlemaps/android-maps-compose

#### Windows Size Class -  Detectar dimensiones de pantallas
https://androidx.tech/artifacts/compose.material3/material3-window-size-class

#### Room - Persistencia de datos
https://github.com/androidx-releases/Room

#### Hamcrest - Comparadores de aserciones personalizados
https://hamcrest.org/JavaHamcrest/javadoc/2.2/

#### Mock Web Server - Servidor web programable para probar clientes HTTP
https://github.com/square/okhttp/tree/master/mockwebserver

#### Mockito - Creación de objetos simulados (mocks) en pruebas unitarias
https://site.mockito.org/


## Test unitarios
Las pruebas unitarias se implementaron en 2 archivos en las rutas que se indican a continuación:


#### app/src/test/java/com/fraporitmos/yapechallengue/presentation/main/viewModel/RecipeViewModelTest.kt 

#### app/src/test/java/com/fraporitmos/yapechallengue/dataAccess/ResponseServerTest.kt 

En estos archivos podemos ejecutar las distintas validaciones de la informacón esencial que se solicitó como requisito para implementar la aplicación, asi mismo se valida la pruebas locales y remotas de la API.



## Funcionalidades extras

Se agregó persistencia de datos a la App, permitir a los usuarios ver las recetas incluso si no se encuentra conectado a internet.

Proguards con R8 para optimizar la aplicación, así se pudo bajar el peso de la App considerablemente (Las imágenes locales tienen la mayor parte del peso, no se optimizaron para mayor calidad).

Detectar conexión              |  Soporte para ver recetas Offline                    
:-------------------------:|:-------------------------:
![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711159119/yape/Screenshot_1711158685-portrait_1_euxizz.png)|![](https://res.cloudinary.com/dokwcwo9t/image/upload/v1711159120/yape/Screenshot_1711158740-portrait_1_hggakx.png)

979507675
962103011
974096555

