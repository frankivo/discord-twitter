FROM oosterhuisf/sbt:1.4.6 as compiler
WORKDIR /root
COPY project/build.properties project/plugins.sbt ./project/
COPY build.sbt .
COPY src src
RUN sbt assembly

FROM openjdk:8-alpine
CMD java -jar /root/*jar
COPY --from=compiler /root/target/scala-2.13/*jar /root
