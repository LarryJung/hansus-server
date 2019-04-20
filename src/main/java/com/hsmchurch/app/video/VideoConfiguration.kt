package com.hsmchurch.app.video

import com.hsmchurch.app.video.support.DescriptionParser
import com.hsmchurch.app.video.support.YoutubeDescriptionParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class VideoConfiguration {

    @Bean(name = ["descriptionParser"])
    open fun descriptionParser(): DescriptionParser = YoutubeDescriptionParser()

}
