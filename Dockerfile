# $ docker build -f /path/to/a/Dockerfile .
# $ docker run -it --rm -p 80:8080 name:tag


FROM clojure:lein-2.8.1

COPY . /usr/src/app

WORKDIR /usr/src/app

# RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" clojure-application.jar

EXPOSE 8080

CMD ["java", "-jar", "target/cornerstone-app.jar"]
