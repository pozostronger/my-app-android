# 📱 Mi Aplicación Android

## 📝 Descripción del Proyecto

Esta aplicación Android tiene como objetivo proporcionar una serie de funcionalidades útiles y simples de usar, incluyendo acceso a cámara, linterna, envío de correos, vista de contactos, SMS, y navegación hacia distintas actividades internas de la app.

Además, se utilizan **intents implícitos y explícitos** para interactuar con el sistema Android y con otras actividades del proyecto.

---

## 🔧 Funcionalidades

### ✅ Eventos Implícitos:

1. **Abrir Aulas Virtuales**: Abre el navegador en la página institucional `https://aulasvirtuales.santotomas.cl`.
2. **Enviar Correo**: Lanza un cliente de correo para enviar un mensaje al correo recibido.
3. **Compartir Texto**: Permite compartir un mensaje desde la app con otras apps (WhatsApp, Gmail, etc.).
4. **Abrir Cámara**: Abre una actividad propia para capturar fotos.
5. **Configuración del Sistema**: Abre la configuración del sistema Android.
6. **Enviar SMS**: Navega a una actividad que permite enviar mensajes SMS.
7. **Ver Contactos**: Abre la lista de contactos del teléfono.
8. **Llamar**: Abre el marcador con un número preestablecido.

### 🔄 Eventos Explícitos:

1. **Ir a Perfil**: Abre una actividad donde se puede editar el nombre del usuario.
2. **Ver Detalle Alumno**: Muestra una nueva pantalla con datos de un estudiante (nombre, nota y asignatura).
3. **Configuración Interna**: Accede a una configuración propia de la aplicación.
4. **Ver Ubicación en Mapa**: Abre un mapa mostrando una ubicación.


## 📋 Requisitos

- Android Studio Flamingo o superior.
- SDK mínimo: Android 7.0 (API 24).
- Conexión a internet para funciones online.
- Dispositivo con flash para el uso de la linterna.
- Permisos requeridos:
  - Cámara
  - Acceso a contactos
  - Envío de SMS (opcional)

## 📸 Capturas de pantalla

#### Vista Aulas
![Vista Aulas](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_aulas.png)

#### Vista Botón Maps
![Vista Botón Maps](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_botonmaps.png)

#### Vista Configuración
![Vista Configuración](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_config.png)

#### Vista Config Interna
![Vista Config Interna](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_confinterna.png)

#### Vista Contactos
![Vista Contactos](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_contactos.png)

#### Vista Detalle
![Vista Detalle](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_detalle.png)

#### Vista Home
![Vista Home](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_home.png)

#### Vista Llamar
![Vista Llamar](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_llamar.png)

#### Vista SMS
![Vista SMS](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_sms.png)

#### Vista Ubicación IP
![Vista Ubicación IP](https://raw.githubusercontent.com/pozostronger/my-app-android/master/Screenshots_Vista/vista_ubicacionIP.png)



## 🚀 Instalación

1. Clona el repositorio:
   git clone https://github.com/pozostronger/my-app-android.git
   
Organizacion de carpetas:
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/devst/app/
│   │   │   │   ├── HomeActivity.java
│   │   │   │   ├── PerfilActivity.java
│   │   │   │   ├── DetalleActivity.java
│   │   │   │   ├── ConfigActivity.java
│   │   │   │   ├── MapActivity.java
│   │   │   │   ├── CamaraActivity.java
│   │   │   │   └── EnviarSMSActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_home.xml
│   │   │   │   │   └── ...
│   │   │   │   ├── values/
│   │   │   │   └── drawable/
├── build.gradle
└── README.md

🧩 Futuras Mejoras

Autenticación de usuarios (login con Firebase).
Base de datos local con Room para guardar configuraciones.
Modo oscuro.
