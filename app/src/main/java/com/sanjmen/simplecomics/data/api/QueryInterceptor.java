package com.sanjmen.simplecomics.data.api;

import com.sanjmen.simplecomics.BuildConfig;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Add default query parameters to every request.
 * <p>
 * apikey - marvel public key param
 * ts - a timestamp (or other long string which can change on a request-by-request basis)
 * hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
 * <p>
 * For example, a user with a public key of "1234" and a private key of "abcd"
 * could construct a valid call as follows:
 * http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150
 */
public class QueryInterceptor implements Interceptor {
    private static final String PARAM_API_KEY = "apikey";
    private static final String PARAM_TIME_STAMP = "ts";
    private static final String PARAM_HASH = "hash";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        String timestamp = getTimeStamp();
        String hash = getMD5Digest(timestamp);

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(PARAM_API_KEY, BuildConfig.MARVEL_API_KEY)
                .addQueryParameter(PARAM_TIME_STAMP, timestamp)
                .addQueryParameter(PARAM_HASH, hash)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String getMD5Digest(String timestamp) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest((timestamp + BuildConfig.MARVEL_SECRET_KEY
                    + BuildConfig.MARVEL_API_KEY).getBytes());
            BigInteger number = new BigInteger(1, messageDigest);

            String md5 = number.toString(16);
            while (md5.length() < 32) {
                md5 = 0 + md5;
            }
            return md5;

        } catch (NoSuchAlgorithmException e) {
            Timber.e("Error on md5 digest: " + e.getMessage());
            return "";
        }
    }
}
