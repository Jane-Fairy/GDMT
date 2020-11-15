package com.config;

import com.utils.RsaUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@ConfigurationProperties("rsa.key")//注入的配置前缀
public class RsaKeyProperties {

    //封装配置的属性，和配置的属性名相同
    private String pubKeyFile;
    private String priKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct //对象构建完成后执行
    public void createRsaKey() throws Exception {
        if (!(new File(pubKeyFile).exists()&&new File(priKeyFile).exists())){
            RsaUtils.generateKey(pubKeyFile,priKeyFile,"gdmt",2048);
        }
        publicKey= RsaUtils.getPublicKey(pubKeyFile);
        privateKey= RsaUtils.getPrivateKey(priKeyFile);
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }

    public String getPriKeyFile() {
        return priKeyFile;
    }

    public void setPriKeyFile(String priKeyFile) {
        this.priKeyFile = priKeyFile;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

}
