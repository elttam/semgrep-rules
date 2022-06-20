package com.example.sparkdemo;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Crypto1 {

    private Key key;
    private GCMParameterSpec gcmParameterSpec;

    public Crypto1() {
        byte[] keyBytes = "YELLOW_SUBMARINE".getBytes();
    byte[] blah = "blah".getBytes();
        // ruleid: gcm-static-iv
        key = new SecretKeySpec(keyBytes, "AES");
        gcmParameterSpec = new GCMParameterSpec(128, keyBytes);
    }

    private synchronized byte[] transform(byte[] data, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(mode, key, gcmParameterSpec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String encrypt(String data) {
        return DatatypeConverter.printHexBinary(transform(data.getBytes(), Cipher.ENCRYPT_MODE)).toLowerCase();
    }

    public String decrypt(String data) {
        return new String(transform(DatatypeConverter.parseHexBinary(data), Cipher.DECRYPT_MODE));
    }
}

public class Crypto2 {

    private Key key;
    private GCMParameterSpec gcmParameterSpec;

    public Crypto2() {
        byte[] keyBytes = {
            (byte) 0, (byte) 0, (byte) 0, (byte) 0,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0,
            (byte) 0, (byte) 0, (byte) 0, (byte) 0
        };
        byte[] blah = "blah".getBytes();
        // ruleid: gcm-static-iv
        key = new SecretKeySpec(keyBytes, "AES");
        byte[] blahblah = "blahblah".getBytes();
        gcmParameterSpec = new GCMParameterSpec(128, keyBytes);
    }

    private synchronized byte[] transform(byte[] data, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(mode, key, gcmParameterSpec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String encrypt(String data) {
        return DatatypeConverter.printHexBinary(transform(data.getBytes(), Cipher.ENCRYPT_MODE)).toLowerCase();
    }

    public String decrypt(String data) {
        return new String(transform(DatatypeConverter.parseHexBinary(data), Cipher.DECRYPT_MODE));
    }
}

public class Crypto3 {


    private Key key;
    private GCMParameterSpec gcmParameterSpec;
    byte[] iv = {
        (byte) 0, (byte) 0, (byte) 0, (byte) 0,
        (byte) 0, (byte) 0, (byte) 0, (byte) 0,
        (byte) 0, (byte) 0, (byte) 0, (byte) 0,
        (byte) 0, (byte) 0, (byte) 0, (byte) 0
    };

    public Crypto3() {
    byte[] blah = "blah".getBytes();
        // ruleid: gcm-static-iv
        gcmParameterSpec = new GCMParameterSpec(128, iv);
        key = new SecretKeySpec(iv, "AES");
    }

    private synchronized byte[] transform(byte[] data, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(mode, key, gcmParameterSpec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String encrypt(String data) {
        return DatatypeConverter.printHexBinary(transform(data.getBytes(), Cipher.ENCRYPT_MODE)).toLowerCase();
    }

    public String decrypt(String data) {
        return new String(transform(DatatypeConverter.parseHexBinary(data), Cipher.DECRYPT_MODE));
    }
}
