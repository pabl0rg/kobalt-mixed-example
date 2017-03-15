package com.guatec;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

public class Example {
	public Example(){
	}
	public boolean isOk(){
		return true;
	}

    public void test() throws Exception{
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setReadTimeout(90, TimeUnit.SECONDS);
        Request getAttachment = new Request.Builder().url("https://www.google.com").build();
        Response response = httpClient.newCall(getAttachment).execute();

        KotlinClassThatExtendsJavaClass kc = new KotlinClassThatExtendsJavaClass();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("two");
    }
}

