# Docker Основы - Mentee Power Course

## Student: Stas

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