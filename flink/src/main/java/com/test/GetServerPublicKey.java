package com.test;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.PublicKey;

public class GetServerPublicKey {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.jd.com");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();

        Certificate[] certs = conn.getServerCertificates();
        X509Certificate cert = (X509Certificate) certs[0];

        PublicKey publicKey = cert.getPublicKey();

        System.out.println("公钥算法: " + publicKey.getAlgorithm());
        System.out.println("公钥格式: " + publicKey.getFormat());
        System.out.println("公钥内容: \n" + publicKey.toString());
    }


    class Solution {
        public int pivotIndex(int[] nums) {
            int[] arr = new int[nums.length];
            arr[0] = 0;
            for(int i = 1; i < nums.length; i++) {
                arr[i] = arr[i-1] + nums[i-1];
            }
            int sum = 0;
            for(int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            for(int i = 0; i < nums.length; i++) {
                int left = arr[i];
                int right = sum - left - nums[i] ;
                if(left == right) {
                    return i;
                }
            }
            return -1;



        }
    }
}
