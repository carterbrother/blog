FROM java:8

RUN echo "Asia/Chongqing" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata
RUN mkdir /app
RUN mkdir /app/logs
ADD app.jar /app/app.jar
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
ENTRYPOINT ["java","-Dserver.port=8080","-jar","app.jar"]