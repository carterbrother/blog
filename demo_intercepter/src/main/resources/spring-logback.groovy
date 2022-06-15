import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO



appender("CONSOLE", ConsoleAppender){
    encoder(PatternLayoutEncoder){
        pattern = "%d{yyyy/MM/dd-HH:mm:ss} %-5level [%thread] %class{5}:%line>>%msg%n"
    }
}

logger("com.springbootpractice.demo_intercepter",INFO)


root(DEBUG,["CONSOLE"])