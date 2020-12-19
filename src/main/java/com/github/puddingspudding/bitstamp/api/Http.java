package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public final class Http {

    private final String HOST = "www.bitstamp.net";
    private final String URL = "https://"+ HOST + "/api/";

    private final String API_KEY;
    private final String API_SECRET_KEY;

    HttpClient client = HttpClient.newBuilder()
        .build();

    public Http(String apiKey, String apiSecretKey) {
        API_KEY = String.format("%s %s", "BITSTAMP", apiKey);
        API_SECRET_KEY = apiSecretKey;
    }

    public CompletableFuture<Ticker> getTicker(final CurrencyPair currencyPair) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URL + "v2/ticker/" + currencyPair.name().toLowerCase()))
            .build();

        Gson gson = new Gson();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(response -> gson.fromJson(response, Ticker.class));

    }

    public CompletableFuture<List<CurrencyPairInfo>> getCurrencyPairInfo() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URL + "v2/trading-pairs-info/"))
            .build();

        Gson gson = new Gson();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(json -> gson.fromJson(json,  new TypeToken<List<CurrencyPairInfo>>(){}.getType()));
    }

    public CompletableFuture<List<Transaction>> getTransactions() throws Exception {
        URI uri = URI.create(URL + "v2/user_transactions/");

        String method = "POST";
        String path = uri.getPath();
        String httpQuery = Optional.ofNullable(uri.getQuery()).orElse("");
        String contentType = "application/x-www-form-urlencoded";
        String nonce = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();
        String version = "v2";
        String payload = "offset=0";
        String signature = this.sign(API_KEY,
            API_SECRET_KEY,
            method,
            HOST,
            path,
            httpQuery,
            contentType,
            nonce,
            timestamp,
            version,
            payload);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(uri)
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .setHeader("Content-Type", contentType);

        HttpRequest request = addAuthHeaders(builder, signature, nonce, timestamp, version)
            .build();

        Gson gson = new Gson();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(json -> gson.fromJson(json,  new TypeToken<List<Transaction>>(){}.getType()));

    }

    public CompletableFuture<List<Order>> getOpenOrders(final CurrencyPair currencyPair) throws Exception {
        URI uri = URI.create(
            URL
            + "v2/open_orders/"
            + Optional.ofNullable(currencyPair).map(CurrencyPair::toName).orElse("all")
            + "/"
        );

        String method = "POST";
        String path = uri.getPath();
        String httpQuery = Optional.ofNullable(uri.getQuery()).orElse("");
        String contentType = "";
        String nonce = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();
        String version = "v2";
        String payload = "";
        String signature = this.sign(API_KEY,
            API_SECRET_KEY,
            method,
            HOST,
            path,
            httpQuery,
            contentType,
            nonce,
            timestamp,
            version,
            payload);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(uri)
            .POST(HttpRequest.BodyPublishers.ofString(payload));

        HttpRequest request = addAuthHeaders(builder, signature, nonce, timestamp, version)
            .build();

        Gson gson = new Gson();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .exceptionally(t -> {
                t.printStackTrace();
                return null;
            })
            .thenApply(HttpResponse::body)
            .thenApply(s -> {
                System.out.println(s);
                return s;
            })
            .thenApply(json -> gson.fromJson(json,  new TypeToken<List<Order>>(){}.getType()));
    }

    public CompletableFuture<Balance> getBalance() throws Exception {
        URI uri = URI.create(URL + "v2/balance/");

        String method = "POST";
        String path = uri.getPath();
        String httpQuery = Optional.ofNullable(uri.getQuery()).orElse("");
        String contentType = "";
        String nonce = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();
        String version = "v2";
        String payload = "";
        String signature = this.sign(API_KEY,
            API_SECRET_KEY,
            method,
            HOST,
            path,
            httpQuery,
            contentType,
            nonce,
            timestamp,
            version,
            payload);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(uri)
            .POST(HttpRequest.BodyPublishers.ofString(payload));

        HttpRequest request = addAuthHeaders(builder, signature, nonce, timestamp, version)
            .build();

        Gson gson = new Gson();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(json -> gson.fromJson(json,  Balance.class));
    }

    public CompletableFuture<Order> buy(
        final Order order,
        double limitPrice,
        final int baseDecimal,
        final int countDecimal
    ) throws Exception {
        URI uri = URI.create(URL + "v2/buy/" + order.getCurrency_pair().toLowerCase() + "/");

        String method = "POST";
        String path = uri.getPath();
        String httpQuery = Optional.ofNullable(uri.getQuery()).orElse("");
        String contentType = "application/x-www-form-urlencoded";
        String nonce = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();
        String version = "v2";
        String payload = String.format(
            String.format("price=%%.%df&amount=%%.%df&limit_price=%%.%df", countDecimal, baseDecimal, countDecimal),
            order.getPrice(),
            order.getAmount(),
            limitPrice
        );
        System.out.println(payload);
        String signature = this.sign(API_KEY,
            API_SECRET_KEY,
            method,
            HOST,
            path,
            httpQuery,
            contentType,
            nonce,
            timestamp,
            version,
            payload);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(uri)
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .setHeader("content-type", "application/x-www-form-urlencoded");

        HttpRequest request = addAuthHeaders(builder, signature, nonce, timestamp, version)
            .build();

        Gson gson = new Gson();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(s -> {
                System.out.println(s);
                return s;
            })
            .thenApply(json -> gson.fromJson(json,  Order.class));
    }

    public void cancelOrder(final Order order) throws Exception {
        URI uri = URI.create(URL + "v2/cancel_order/");

        String method = "POST";
        String path = uri.getPath();
        String httpQuery = Optional.ofNullable(uri.getQuery()).orElse("");
        String contentType = "application/x-www-form-urlencoded";
        String nonce = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();
        String version = "v2";
        String payload = "id=" + order.getId();
        String signature = this.sign(API_KEY,
            API_SECRET_KEY,
            method,
            HOST,
            path,
            httpQuery,
            contentType,
            nonce,
            timestamp,
            version,
            payload);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(uri)
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .setHeader("content-type", "application/x-www-form-urlencoded");

        HttpRequest request = addAuthHeaders(builder, signature, nonce, timestamp, version)
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(response -> {
                System.out.println(response.body());
                if (response.statusCode() != 200) {
                    throw new RuntimeException(response.body());
                }
            })
            .exceptionally(throwable -> {
                throwable.printStackTrace();
                //System.exit(1);
                return null;
            })
            .join();
    }

    private HttpRequest.Builder addAuthHeaders(
        HttpRequest.Builder requestBuild,
        String signature,
        String nonce,
        Instant timestamp,
        String version
    ) {
        return requestBuild
            .setHeader("X-Auth", API_KEY)
            .setHeader("X-Auth-Signature", signature)
            .setHeader("X-Auth-Nonce", nonce)
            .setHeader("X-Auth-Timestamp", String.valueOf(timestamp.toEpochMilli()))
            .setHeader("X-Auth-Version", version);
    }

    private String sign(
        String apiKey,
        String apiKeySecret,
        String httpMethod,
        String httpHost,
        String httpPath,
        String httpQuery,
        String contentType,
        String nonce,
        Instant timestamp,
        String version,
        String payload
    ) throws Exception {
        String signature = apiKey +
            httpMethod +
            httpHost +
            httpPath +
            httpQuery +
            contentType +
            nonce +
            timestamp.toEpochMilli() +
            version +
            payload;

        SecretKeySpec secretKey = new SecretKeySpec(apiKeySecret.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] rawHmac = mac.doFinal(signature.getBytes());
        signature = Hex.encodeHexString(rawHmac).toUpperCase();

        return signature;
    }

}
