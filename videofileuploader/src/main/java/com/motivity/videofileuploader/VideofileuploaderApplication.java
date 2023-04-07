package com.motivity.videofileuploader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideofileuploaderApplication {

	public static void main(String[] args) {
//		String uri="https://jsonplaceholder.typicode.com/users?id=7";
//		HttpRequest request=HttpRequest.newBuilder(URI.create(uri)).GET().version(HttpClient.Version.HTTP_2).build();
//		HttpClient client=HttpClient.newBuilder().build();
//		try{
//
//			HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(send.body());
//		}
//		catch (Exception e){
//			System.out.println(e);
//		}
		SpringApplication.run(VideofileuploaderApplication.class, args);
	}

}
