package com.api.code;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilis {

	public RequestSpecification req;
	public ResponseSpecification res;

	public RequestSpecification requestSpecification() {
		req = new RequestSpecBuilder().setBaseUri("https://api.github.com")
				.addHeader("Authorization", "Bearer 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").addHeader("Content-Type", "application/json")
				.build();

		return req;
	}

	//ResponseSpecification is used for defining and encapsulating the expected properties and validations of a response. 
	//It allows you to specify what you expect from the response in terms of status code, headers, cookies, and more.
	//--------------------
	//ResponseBuilder is not a standard class in RestAssured. 
	//It seems you might be referring to Response objects that you get when making requests and extracting responses.
	
	public ResponseSpecification responseSpecification() {
		res = new ResponseSpecBuilder().log(LogDetail.ALL).expectContentType(ContentType.JSON).build();
		return res;

	}
	
	public ResponseSpecification responseSpecification2() {
		res = new ResponseSpecBuilder().log(LogDetail.ALL).build();
		return res;

	}
	
}
