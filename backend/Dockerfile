FROM openjdk:22-jdk-slim

# Actualizar e instalar wget, xz-utils y Python 3
RUN apt-get update -y && \
    apt-get install -y wget xz-utils python3 python3-pip

# Descargar e instalar ffmpeg
RUN wget https://johnvansickle.com/ffmpeg/releases/ffmpeg-release-amd64-static.tar.xz && \
    tar -xvf ffmpeg-release-amd64-static.tar.xz && \
    cp ffmpeg-*/ffmpeg /usr/local/bin/ && \
    cp ffmpeg-*/ffprobe /usr/local/bin/

# Descargar yt-dlp
RUN wget https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp -O /usr/local/bin/yt-dlp && \
    chmod a+rx /usr/local/bin/yt-dlp

WORKDIR /app

COPY target/YoutubeDownloader.jar /app/

EXPOSE 8081

CMD ["java", "-jar", "YoutubeDownloader.jar"]
