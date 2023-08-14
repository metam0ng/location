package com.location.api.server.config;

import com.location.common.config.CommonConfig;
import com.location.external.client.config.LocationExternalClientConfig;
import com.location.repository.config.RepositoryConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CommonConfig.class,
        RepositoryConfig.class,
        LocationExternalClientConfig.class
})
public class ServerConfig {

}
