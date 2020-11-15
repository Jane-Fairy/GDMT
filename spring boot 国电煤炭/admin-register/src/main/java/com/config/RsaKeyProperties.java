package com.config;

import com.utils.RsaUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@ConfigurationProperties("rsa.key")//注入的配置前缀
public class RsaKeyProperties {

    //封装配置的属性，和配置的属性名相同
    private String pubKeyFile;
    private PublicKey publicKey;

    @PostConstruct //对象构建完成后执行
    public void createRsaKey() throws Exception {
        publicKey= RsaUtils.getPublicKey(pubKeyFile);
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

}
