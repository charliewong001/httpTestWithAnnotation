package utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SSLClientWithCerFactory {
    public static CloseableHttpClient getClient()
            throws KeyStoreException, NoSuchAlgorithmException,
            CertificateException, IOException, KeyManagementException {
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream in = SSLClientWithCerFactory.class
                .getResourceAsStream("/clientTrust.keystore");
        trustStore.load(in, "123456".toCharArray());

        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                .build();

        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(
                sslContext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        // SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(
        // sslContext, new String[] { "TLSv1" }, null,
        // SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setSSLSocketFactory(factory).build();
        return closeableHttpClient;
    }
}