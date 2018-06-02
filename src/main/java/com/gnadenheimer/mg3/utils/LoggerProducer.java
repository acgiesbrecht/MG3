package com.gnadenheimer.mg3.utils;

import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import org.slf4j.Logger;

@Singleton
public class LoggerProducer {

    @Produces Logger createLogger(final InjectionPoint ip){
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }

}
