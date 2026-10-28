# Каталог молекул

Учебное приложение на Kotlin и Compose Multiplatform. Предметная область — химические соединения из ChEMBL. В первой вехе данные берутся из мокового репозитория, сетевых запросов нет.

В каталоге можно искать молекулы по названию, открывать их свойства и переходить к родительскому соединению, если оно есть в наборе. Например: NALIDIXATE SODIUM → NALIDIXIC ACID. Кнопка «Назад» возвращает на предыдущий экран.

Есть светлая и тёмная темы, русский и английский интерфейс. Названия молекул не переводятся. Настройки не сохраняются на диск.

## Запуск Desktop

Команды выполняются из корня репозитория. Нужна установленная Java; для Gradle в проекте задан JDK 21. При первом запуске Gradle загружает зависимости, поэтому нужен интернет.

Windows, PowerShell:

```powershell
.\gradlew.bat :desktopApp:run
```

Linux / macOS:

```bash
./gradlew :desktopApp:run
```

## Язык интерфейса

По умолчанию используется локаль системы. Для английского языка есть перевод, для остальных локалей используются русские строки из основного набора ресурсов. Переключателя языка в интерфейсе нет.

Чтобы явно выбрать язык, закройте приложение и запустите его с параметром `appLanguage`.

Windows, PowerShell:

```powershell
# Английский
.\gradlew.bat :desktopApp:run -PappLanguage=en
# Русский
.\gradlew.bat :desktopApp:run -PappLanguage=ru
```

Linux / macOS:

```bash
./gradlew :desktopApp:run -PappLanguage=en
./gradlew :desktopApp:run -PappLanguage=ru
```

Параметр передаётся JVM приложения как `user.language` и действует на этот запуск, включая заголовок окна.

## Другие платформы

Web (Wasm):

```powershell
.\gradlew.bat :webApp:wasmJsBrowserDevelopmentRun
```

Web (JavaScript):

```powershell
.\gradlew.bat :webApp:jsBrowserDevelopmentRun
```

Сборка Android APK (нужен Android SDK):

```powershell
.\gradlew.bat :androidApp:assembleDebug
```

Эта команда собирает APK; для запуска установите его на устройство или используйте Android Studio с эмулятором. На Linux / macOS замените `.\gradlew.bat` на `./gradlew`.

## Структура

- `shared` — модели, моковый репозиторий, ViewModel, экраны, навигация, тема и строковые ресурсы.
- `desktopApp`, `androidApp`, `webApp` — точки входа для платформ.
- `docs/lab-1` — материалы первой лабораторной работы.
- `tools/check-strings.py` — проверка совпадения ключей русских и английских строк.

## Проверка

Компиляция Desktop без открытия окна:

```powershell
.\gradlew.bat :desktopApp:classes
```

Проверка ключей локализации (нужен Python 3):

```powershell
python tools/check-strings.py
```

Ручная проверка:

1. Найти молекулу по части названия; ввести несуществующее название и увидеть «Ничего не найдено».
2. Открыть NALIDIXATE SODIUM, перейти к родительскому соединению, вернуться по одному экрану до каталога.
3. Переключить тему, открыть деталь и вернуться.
4. Запустить приложение на русском и английском, проверить подписи и название окна.
