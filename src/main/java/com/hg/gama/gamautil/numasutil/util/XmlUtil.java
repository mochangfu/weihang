package com.hg.gama.gamautil.numasutil.util;


import com.hg.gama.gamautil.numasutil.exception.GamaException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlUtil {

    public static <T> T toObject(File file, Class<T> clz) {
        try {
            Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(clz).createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(file);
        } catch (Exception e) {
            throw new GamaException("反序列化失败", e);
        }
    }
}
