package com.springx.bootdubbo.starter.cache.core.utils.serial;

import java.io.IOException;

public interface Serializer {

    String name();

    byte[] serialize(Object obj) throws IOException;

    Object deserialize(byte[] bytes) throws IOException;

}