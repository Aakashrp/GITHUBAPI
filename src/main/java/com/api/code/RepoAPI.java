package com.api.code;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.apache.poi.sl.usermodel.ObjectMetaData.Application;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RepoAPI extends Utilis {

	// POST Request

	@Test(priority = 1)
	public void postRepo() {

		System.out.println("=============================POST REQ=============================");
		File json1 = new File("F:\\API\\GitHub Json\\Given.json");

		String response = given().spec(requestSpecification()).pathParam("org", "TestAPIS").body(json1).when()
				.post("/orgs/{org}/repos").then().spec(responseSpecification()).statusCode(201).extract().response()
				.asString();

	}

	@Test(priority = 2)
	public void getRep() {
		System.out.println("=============================GET REQ=============================");

		String res = given().spec(requestSpecification()).pathParams("owner", "TestAPIS", "repo", "APl").when()
				.get("/repos/{owner}/{repo}").then().spec(responseSpecification()).statusCode(200).extract().response()
				.asString();

		JsonPath js = new JsonPath(res);
		String actulReponame = js.getString("name");
		assertEquals(actulReponame, "APl");

	}

	@Test(priority=3)
	public void putRep() {
		
		System.out.println("=============================PUT REQ=============================");
		
		File json1 = new File("F:\\API\\GitHub Json\\JsonPut.json");
		String putbody ="{\"message\":\"my commit message\",\"committer\":{\"name\":\"Monalisa Octocat\",\"email\":\"octocat@github.com\"},\"content\":\"bXkgbmV3IGZpbGUgY29udGVudHM=\"}";
		String res=given().spec(requestSpecification()).
                pathParams("owner","TestAPIS","repo","APl").
                body(putbody).
                when().put("/repos/{owner}/{repo}/contents/C.java").
                then().spec(responseSpecification()).assertThat().statusCode(201).extract().response().asString();
		
        JsonPath js =new JsonPath(res);
        String filename =js.getString("content.name");
        assertEquals(filename,"C.java");

	}

	@Test(priority = 4)
	public void deleteRep() {
		given().spec(requestSpecification())
				.pathParams("owner", "TestAPIS", "repo", "Hello-World").when().delete("/repos/{owner}/{repo}").then()
				.spec(responseSpecification2()).assertThat().statusCode(204);

	}
}
