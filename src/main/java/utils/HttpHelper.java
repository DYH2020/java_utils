package utils;


import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpHelper {
//    private static final String DEFAULT_CHARSET = "utf-8";
//    private String charset = "utf-8";
//    private Integer connectTimeout = null;
//    private Integer socketTimeout = null;
//    private String proxyHost = null;
//    private Integer proxyPort = null;
//
//    /**
//     * Do GET request
//     * @param url
//     * @return
//     * @throws Exception
//     * @throws IOException
//     */
//    public static String doGet(String url) throws Exception {
//
//        URL localURL = new URL(url);
//
//        URLConnection connection = openConnection(localURL);
//        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
//
//        httpURLConnection.setRequestProperty("Accept-Charset", DEFAULT_CHARSET);
//        httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
//
//        InputStream inputStream = null;
//        InputStreamReader inputStreamReader = null;
//        BufferedReader reader = null;
//        StringBuffer resultBuffer = new StringBuffer();
//        String tempLine = null;
//
//        if (httpURLConnection.getResponseCode() >= 300) {
//            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
//        }
//
//        try {
//            inputStream = httpURLConnection.getInputStream();
//            inputStreamReader = new InputStreamReader(inputStream);
//            reader = new BufferedReader(inputStreamReader);
//
//            while ((tempLine = reader.readLine()) != null) {
//                resultBuffer.append(tempLine);
//            }
//
//        } finally {
//
//            if (reader != null) {
//                reader.close();
//            }
//
//            if (inputStreamReader != null) {
//                inputStreamReader.close();
//            }
//
//            if (inputStream != null) {
//                inputStream.close();
//            }
//
//        }
//
//        return resultBuffer.toString();
//    }
//
//    /**
//     * Do POST request
//     * @param url
//     * @param parameterMap
//     * @return
//     * @throws Exception
//     */
//    public String doPost(String url, Map parameterMap) throws Exception {
//
//        /* Translate parameter map to parameter date string */
//        StringBuffer parameterBuffer = new StringBuffer();
//        if (parameterMap != null) {
//            Iterator iterator = parameterMap.keySet().iterator();
//            String key = null;
//            String value = null;
//            while (iterator.hasNext()) {
//                key = (String)iterator.next();
//                if (parameterMap.get(key) != null) {
//                    value = (String)parameterMap.get(key);
//                } else {
//                    value = "";
//                }
//
//                parameterBuffer.append(key).append("=").append(value);
//                if (iterator.hasNext()) {
//                    parameterBuffer.append("&");
//                }
//            }
//        }
//
//        System.out.println("POST parameter : " + parameterBuffer.toString());
//
//        URL localURL = new URL(url);
//
//        URLConnection connection = openConnection(localURL);
//        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
//
//        httpURLConnection.setDoOutput(true);
//        httpURLConnection.setRequestMethod("POST");
//        httpURLConnection.setRequestProperty("Accept-Charset", charset);
//        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.length()));
//
//        OutputStream outputStream = null;
//        OutputStreamWriter outputStreamWriter = null;
//        InputStream inputStream = null;
//        InputStreamReader inputStreamReader = null;
//        BufferedReader reader = null;
//        StringBuffer resultBuffer = new StringBuffer();
//        String tempLine = null;
//
//        try {
//            outputStream = httpURLConnection.getOutputStream();
//            outputStreamWriter = new OutputStreamWriter(outputStream);
//
//            outputStreamWriter.write(parameterBuffer.toString());
//            outputStreamWriter.flush();
//
//            if (httpURLConnection.getResponseCode() >= 300) {
//                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
//            }
//
//            inputStream = httpURLConnection.getInputStream();
//            inputStreamReader = new InputStreamReader(inputStream);
//            reader = new BufferedReader(inputStreamReader);
//
//            while ((tempLine = reader.readLine()) != null) {
//                resultBuffer.append(tempLine);
//            }
//
//        } finally {
//
//            if (outputStreamWriter != null) {
//                outputStreamWriter.close();
//            }
//
//            if (outputStream != null) {
//                outputStream.close();
//            }
//
//            if (reader != null) {
//                reader.close();
//            }
//
//            if (inputStreamReader != null) {
//                inputStreamReader.close();
//            }
//
//            if (inputStream != null) {
//                inputStream.close();
//            }
//
//        }
//
//        return resultBuffer.toString();
//    }
//
//    private URLConnection openConnection(URL localURL) throws IOException {
//        URLConnection connection;
//        if (proxyHost != null && proxyPort != null) {
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
//            connection = localURL.openConnection(proxy);
//        } else {
//            connection = localURL.openConnection();
//        }
//        return connection;
//    }
//
//    private static HttpURLConnection getConnection(URL url, String method, String ctype) throws IOException {
//        HttpURLConnection conn = null;
//        if ("https".equals(url.getProtocol())) {
//            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
//            connHttps.setSSLSocketFactory(socketFactory);
//            connHttps.setHostnameVerifier(verifier);
//            conn = connHttps;
//        } else {
//            conn = (HttpURLConnection) url.openConnection();
//        }
//
//        conn.setRequestMethod(method);
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html,*/*");
//        conn.setRequestProperty("User-Agent", "aop-sdk-java");
//        conn.setRequestProperty("Content-Type", ctype);
//        return conn;
//    }
//
//    /**
//     * Render request according setting
//     * @param connection
//     */
//    private void renderRequest(URLConnection connection) {
//
//        if (connectTimeout != null) {
//            connection.setConnectTimeout(connectTimeout);
//        }
//
//        if (socketTimeout != null) {
//            connection.setReadTimeout(socketTimeout);
//        }
//
//    }
//
//    /*
//     * Getter & Setter
//     */
//    public Integer getConnectTimeout() {
//        return connectTimeout;
//    }
//
//    public void setConnectTimeout(Integer connectTimeout) {
//        this.connectTimeout = connectTimeout;
//    }
//
//    public Integer getSocketTimeout() {
//        return socketTimeout;
//    }
//
//    public void setSocketTimeout(Integer socketTimeout) {
//        this.socketTimeout = socketTimeout;
//    }
//
//    public String getProxyHost() {
//        return proxyHost;
//    }
//
//    public void setProxyHost(String proxyHost) {
//        this.proxyHost = proxyHost;
//    }
//
//    public Integer getProxyPort() {
//        return proxyPort;
//    }
//
//    public void setProxyPort(Integer proxyPort) {
//        this.proxyPort = proxyPort;
//    }
//
//    public String getCharset() {
//        return charset;
//    }
//
//    public void setCharset(String charset) {
//        this.charset = charset;
//    }

    private static final String DEFAULT_CHARSET = "utf-8";

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    private static SSLContext ctx = null;

    private static HostnameVerifier verifier = null;

    private static SSLSocketFactory socketFactory = null;


    static {

        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());

            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);

            socketFactory = ctx.getSocketFactory();
        } catch (Exception e) {

        }

        verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return false;//默认认证不通过，进行证书校验。
            }
        };

    }

    private static class DefaultTrustManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }


    /**
     * Do GET request
     *
     * @param url
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String doGet(String url) throws Exception {

        URL localURL = new URL(url);

//        URLConnection connection = getConnection(localURL, METHOD_GET, "");
        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestProperty("Accept-Charset", DEFAULT_CHARSET);
//        httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, DEFAULT_CHARSET);
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params, String charset) throws IOException {
        HttpURLConnection conn = null;
        String rsp = null;

        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            String query = buildQuery(params, charset);
            try {
                conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype);
            } catch (IOException e) {
                //Map<String, String> map = getParamsFromUrl(url);
                throw e;
            }

            try {
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                //Map<String, String> map = getParamsFromUrl(url);
                throw e;
            }

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype) throws IOException {
        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(socketFactory);
            connHttps.setHostnameVerifier(verifier);
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
//        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html,*/*");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        conn.setRequestProperty("User-Agent", "aop-sdk-java");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    private static URL buildGetUrl(String strUrl, String query) throws IOException {
        URL url = new URL(strUrl);
        if (StringHelper.isNullOrEmpty(query)) {
            return url;
        }

        if (StringHelper.isNullOrEmpty(url.getQuery())) {
            if (strUrl.endsWith("?")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "?" + query;
            }
        } else {
            if (strUrl.endsWith("&")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "&" + query;
            }
        }

        return new URL(strUrl);
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
//        Set<Entry<String, String>> entries = params.entrySet();
//        boolean hasParam = false;
//
//        for (Entry<String, String> entry : entries) {
//            String name = entry.getKey();
//            String value = entry.getValue();
//            // 忽略参数名或参数值为空的参数
//            if (StringUtil.areNotEmpty(name, value)) {
//                if (hasParam) {
//                    query.append("&");
//                } else {
//                    hasParam = true;
//                }
//
//                query.append(name).append("=").append(URLEncoder.encode(value, charset));
//            }
//        }

        return query.toString();
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringHelper.isNullOrEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringHelper.isNullOrEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringHelper.isNullOrEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }


    private static URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;

        connection = localURL.openConnection();

        return connection;
    }


//    public static void main(String[] args) throws Exception {
//
//
//       String params = "?sn=SDK-NFY-020-00011&pwd=NFYYYYGHAPP&mobile=18520996066&content=&ext=1";
//
//
//        String url = "http://yygh1.dept.nfyy.com/csp/oep/DHC.OEP.BS.OEPSTANWebService.cls?soap_method=QuerySchedule&Input=%3CRequest%3E%3CHospitalId%3E1000%3C%2FHospitalId%3E%3CExtOrgCode%3E%E5%8D%97%E6%96%B9%E5%8C%BB%E5%8A%A1%E9%80%9A%3C%2FExtOrgCode%3E%3CExtUserID%3ENFYWT%3C%2FExtUserID%3E%3CClientType%3E%3C%2FClientType%3E%3CTradeCode%3E1004%3C%2FTradeCode%3E%3CDeptType%3E%3C%2FDeptType%3E%3CDoctorCode%3E%3C%2FDoctorCode%3E%3CSessType%3E%3C%2FSessType%3E%3CStartDate%3E2017-09-28%3C%2FStartDate%3E%3CEndDate%3E2017-09-28%3C%2FEndDate%3E%3CRBASSessionCode%3E%3C%2FRBASSessionCode%3E%3CServiceCode%3E%3C%2FServiceCode%3E%3CDoctorCode%3E%3C%2FDoctorCode%3E%3CStopScheduleFlag%3E%3C%2FStopScheduleFlag%3E%3CDepartmentCode%3E%3C%2FDepartmentCode%3E%3CSearchCode%3E%3C%2FSearchCode%3E%3C%2FRequest%3E";
//
////        url = "https://cn.bing.com/";
//        url="http://121.14.17.208/Server/SMS_Send.aspx";
//
//        url = url + params;
//
//        String result = HttpHelper.doGet(url);
//        System.out.println(result);
////【南方医院】
//
////        System.out.println(new HttpHelper().doPost("http://localhost:8080/OneHttpServer/", dataMap));
//
//        /* Get Request */
////        System.out.println(new HttpHelper().doGet("http://localhost:8080/OneHttpServer/"));
//    }
}
