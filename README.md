# Docker Основы - Mentee Power Course

### Student: Stas

## Docker образы для Java приложений

## Deploy в GitHub Container Registry

### URL образа

```bash
ghcr.io/Mr-Luckyman/mcloud-core:latest
```

### CI/CD Pipeline

При каждом push в ветку `master` автоматически запускается GitHub Actions workflow, который:

1. Собирает Spring Boot приложение через Gradle
2. Создает оптимизированный Docker образ
3. Публикует его в GitHub Container Registry

### Теги образа

| Тег            | Описание                                                     |
|----------------|--------------------------------------------------------------|
| `latest`       | Последняя стабильная версия                                  |
| `master-{SHA}` | Конкретная версия по SHA коммита (например `master-a1b2c3d`) |

### Локальное тестирование образа из registry

1. Скачать образ
   ```bash
   docker pull ghcr.io/Mr-Luckyman/mcloud-core:latest
   ```

2. Запустить контейнер
   ```bash
   docker run -d -p 8082:8082 --name mcloud-spring ghcr.io/Mr-Luckyman/mcloud-core:latest
   ```
3. Проверить работу
   ```bash
   curl http://localhost:8082/health
   curl http://localhost:8082/
   ```

4. Остановить и удалить
   ```bash
   docker stop mcloud-spring
   docker rm mcloud-spring
   ```

### Описание проекта

Проект содержит несколько Java приложений, упакованных в Docker образы:

- **Калькулятор** - консольная утилита для арифметических операций
- **Веб-сервер** - простой HTTP сервер с выводом текущего времени
- **Игра "Угадай число"** - интерактивная консольная игра
- **Анализатор текста** - утилита для статистического анализа текста

### Инструкция по запуску

1. Сборка всех образов
   ```bash
      docker-compose build
   ```

2. Запуск приложений

   **Калькулятор**
    - С аргументами по умолчанию (0 + 0)
        ```bash
        docker-compose run calculator
        ```
    - С пользовательскими аргументами
       ```bash
       docker-compose run calculator 10 + 5
       docker-compose run calculator 20 "*" 3
       docker-compose run calculator 100 / 4
       docker-compose run calculator 15 - 7
       ```
   **Веб-сервер**
    - Запуск в фоне
       ```bash
       docker-compose up -d webserver
       ````
    - Проверка работы
        ```bash
        curl http://localhost:8080
       ```
    - Просмотр логов
        ```bash
        docker-compose logs -f webserver
        ```
    - Остановка
       ```bash
       docker-compose stop webserver
       ```
   **Игра "Угадай число"**
    - Запуск игры
       ```bash
       docker-compose run game
       ```
    - Пример игры:
       ```bash
       🎲 Добро пожаловать в игру 'Угадай число'!
       Я загадал число от 1 до 100. Попробуйте угадать!
       Ваше предположение: 50
       Меньше! 🔽
       Ваше предположение: 25
       Больше! 🔼
       Ваше предположение: 37
       🎉 Поздравляю! Вы угадали число 37!
       ```
   **Анализатор текста**
    - Анализ текста на русском
       ```bash
        docker-compose run analyzer "Привет мир! Привет Docker! Привет всем"
       ```
    - Анализ английского текста
       ```bash
      docker-compose run analyzer "one two three four five six seven eight nine ten"
       ```

3. Управление контейнерами
    - Просмотр всех запущенных контейнеров
      ```bash
      docker-compose ps
      ```
    - Просмотр всех образов
       ```bash
       docker images | findstr calculator
       docker images | findstr webserver
       docker images | findstr game
       docker images | findstr analyzer
       ```
    - Остановка всех контейнеров
       ```bash
      docker-compose down
       ```
    - Остановка и удаление всех контейнеров и образов
       ```bash
       docker-compose down --rmi all
      ```

### Примеры работы

**Калькулятор**

```bash
docker-compose run calculator 10 + 5
Результат: 15.0

docker-compose run calculator 20 "*" 3
Результат: 60.0
```

**Веб-сервер**

```bash
curl http://localhost:8080
Hello from Docker! Текущее время: 2026-03-05T12:34:56.789
```

**Игра**

```bash
docker-compose run game
🎲 Добро пожаловать в игру 'Угадай число'!
Я загадал число от 1 до 100. Попробуйте угадать!
Ваше предположение: 42
🎉 Поздравляю! Вы угадали число 42!
Количество попыток: 1
```

**Анализатор**

```bash
docker-compose run analyzer "один два два три три три"
📊 Анализ текста
=================
Общее количество символов: 20
Буквы: 15
Цифры: 0
Пробелы: 5
Количество слов: 6

ТОП-3 частых слова:
три: 3
два: 2
один: 1
```

## Оптимизация Spring Boot Docker образа

### Сравнение размеров

| Образ                                 | Размер | Экономия       |
|---------------------------------------|--------|----------------|
| `spring-boot:fat` (оригинальный)      | 1.33GB | -              |
| `spring-boot:slim` (оптимизированный) | 325MB  | **~1GB (77%)** |

## Сборка образов

#### Сборка неоптимизированного образа

```bash
docker build -f Dockerfile.original -t spring-boot:fat .
```

#### Сборка оптимизированного образа

```bash
docker build -f Dockerfile.optimized -t spring-boot:slim .
```

#### Проверка размеров

```bash
docker images | findstr spring-boot
```

## docker-hello-output

```bash
C:\Users\Stas> docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
17eec7bbc9d7: Pull complete
ea52d2000f90: Download complete
Digest: sha256:ef54e839ef541993b4e87f25e752f7cf4238fa55f017957c2eb44077083d7a6a
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

## docker-images

```bash
C:\Users\Stas>docker images

IMAGE                ID             DISK USAGE   CONTENT SIZE   EXTRA
hello-world:latest   ef54e839ef54       25.9kB         9.52kB    U
```

## docker-history

```bash
C:\Users\Stas>docker history hello-world
IMAGE          CREATED        CREATED BY                SIZE      COMMENT
ef54e839ef54   6 months ago   CMD ["/hello"]            0B        buildkit.dockerfile.v0
<missing>      6 months ago   COPY hello / # buildkit   16.4kB    buildkit.dockerfile.v0
```

## docker-inspect

```json
[
  {
    "Id": "sha256:ef54e839ef541993b4e87f25e752f7cf4238fa55f017957c2eb44077083d7a6a",
    "RepoTags": [
      "hello-world:latest"
    ],
    "RepoDigests": [
      "hello-world@sha256:ef54e839ef541993b4e87f25e752f7cf4238fa55f017957c2eb44077083d7a6a"
    ],
    "Comment": "buildkit.dockerfile.v0",
    "Created": "2025-08-08T19:05:17Z",
    "Config": {
      "Env": [
        "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
      ],
      "Cmd": [
        "/hello"
      ],
      "WorkingDir": "/"
    },
    "Architecture": "amd64",
    "Os": "linux",
    "Size": 16304,
    "RootFS": {
      "Type": "layers",
      "Layers": [
        "sha256:53d204b3dc5ddbc129df4ce71996b8168711e211274c785de5e0d4eb68ec3851"
      ]
    },
    "Metadata": {
      "LastTagTime": "2026-03-04T08:59:06.633074287Z"
    },
    "Descriptor": {
      "mediaType": "application/vnd.oci.image.index.v1+json",
      "digest": "sha256:ef54e839ef541993b4e87f25e752f7cf4238fa55f017957c2eb44077083d7a6a",
      "size": 12342
    },
    "Identity": {
      "Pull": [
        {
          "Repository": "docker.io/library/hello-world"
        }
      ]
    }
  }
]
```

## docker-containers

```bash
C:\Users\Stas>docker ps -a
CONTAINER ID   IMAGE         COMMAND    CREATED          STATUS                      PORTS     NAMES
4aea496cbaaf   hello-world   "/hello"   8 minutes ago    Exited (0) 8 minutes ago              beautiful_proskuriakova
01d9e1a9e6e3   hello-world   "/hello"   15 minutes ago   Exited (0) 15 minutes ago             hopeful_hugle
```

## docker-disk-usage

```bash
C:\Users\Stas>docker system df
TYPE            TOTAL     ACTIVE    SIZE      RECLAIMABLE
Images          1         1         103.8kB   103.8kB (100%)
Containers      2         0         8.192kB   8.192kB (100%)
Local Volumes   7         0         257MB     257MB (100%)
Build Cache     0         0         0B        0B
```

### Вопрос-ответ:

1. Какая версия Docker установлена на вашей системе?
    - Docker version 29.2.1
2. Сколько слоев в hello-world образе?
    - 2 (`CMD ["/hello"]`, `COPY hello`)
3. Какой размер hello-world образа?
    - 25.9 KB
4. Что делает команда CMD в hello-world?
    - При запуске контейнера будет запущен бинарный файл "/hello",
      который выведет сообщение `"Hello from Docker!"`
5. Почему hello-world имеет такой размер?
    - Это простая программа, которая только выводит приветственное сообщение
      и не загружает никаких зависимостей.
6. Какая разница между docker pull и docker run?
    - `run` - запускает и если нет такого образа, скачивает.
      `pull` - только скачивает образ.
7. Где хранятся Docker образы на вашей системе?
    - C:\Users\Stas\AppData\Local\Docker\wsl\main\ext4.vhdx
8. Что показывает docker info о вашей системе?
    - Версия docker, тип системы для docker, ресурсы для него, количество образов/контейнеров и т.д.
9. Сколько контейнеров hello-world вы запустили (docker ps -a)?
    - 2
10. Какие образы оказались самыми большими и почему?
    - ubuntu. Содержит ОС и необходимые зависимости. Больше слоёв из всех.