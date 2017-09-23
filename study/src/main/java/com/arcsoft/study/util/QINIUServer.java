package com.arcsoft.study.util;

import com.qiniu.util.Auth;

public class QINIUServer {
    private final static String accessKey = "UYEB6LnIvHrGOXi4OWQOdtHsJ450EUUupiDhLzAy";
    private final static String secretKey = "FpnI-gIs9QWlvTRgzPNDNfQBtLegtWxZOwUsgxY8";
    private final static String bucket = "arcsoft";

    public static Auth getAuth() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth;
    }

    public static String getUpToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    public static String getBucket() {
        return bucket;
    }
}
