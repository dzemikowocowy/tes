package util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by israel on 08/07/2015.
 */
public class HttpHelper {

    private static HttpHelper instance = null;

    public static HttpHelper getInstance() {
        if(instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }

    public void postRequest(String postRequest) {
        try {
            HttpClients.createDefault().execute(new HttpPost(postRequest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRequest(String deleteRequest) {
        try {
            HttpClients.createDefault().execute(new HttpDelete(deleteRequest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isServerOnline (String serverIpAddress) {
        // Get hostname and port
        String[] ipAndPort = serverIpAddress.split(":");
        String hostname = ipAndPort[0];
        int port;
        if(ipAndPort.length == 2)
            port = Integer.valueOf(ipAndPort[1]);
        else
            port = 80;

        // Check connection
        SocketAddress sockaddr = new InetSocketAddress(hostname, port);
        Socket socket = new Socket();
        boolean online = true;
        try {
            socket.connect(sockaddr, 10000);
        } catch (IOException iOException) {
            online = false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}

        }
        return online;

    }


    public String getRequestHeaderValue(String getRequest, String headerName) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(getRequest);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                return response.getFirstHeader(headerName).getValue();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
