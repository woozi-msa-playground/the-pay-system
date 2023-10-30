package com.practice.common.config

import com.thoughtworks.xstream.XStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {

    @Bean
    fun xStream(): XStream {
        val xStream = XStream()
        xStream.allowTypesByWildcard(
            arrayOf(
                "com.practice.**",
                "commands.**",
                "events.**"
            )
        )
        return xStream
    }
}