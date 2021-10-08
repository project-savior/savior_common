package com.jerry.savior_common.config;


import com.jerry.savior_common.defaultImpl.DefaultTokenExtractor;
import com.jerry.savior_common.interfaces.TokenExtractor;
import com.jerry.savior_common.util.TokenHelper;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22454
 */
@Configuration
public class DefaultTokenHelperConfig {
    public static final Long TOKEN_EXPIRE = 24 * 60 * 60 * 1000L;
    public static final String TOKEN_SECRET = "my_token";

    @Bean
    @ConditionalOnMissingBean
    public TokenHelper tokenHelper() {
        TokenExtractor parser = new DefaultTokenExtractor();
        return new TokenHelper(TOKEN_EXPIRE, TOKEN_SECRET, parser);
    }
}
