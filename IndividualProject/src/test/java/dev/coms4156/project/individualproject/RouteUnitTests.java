package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.HashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.context.ContextConfiguration;

/**
 * This is just testing if we can read our database from the ./data.txt file
 * and extract departments & courses from the database.
 */
@SpringBootTest
@ContextConfiguration
public class RouteUnitTests {
  
  /**
   * We are creating a database with two departments (COMS & ECON) and one courses per department
   * for simplicity sake. Deleteing the files for a cleaner testing environment. 
   */
  @BeforeAll
  public static void setupDatabaseForTesting() {

    testRouteController = new RouteController();
    
  }

  // /**
  //  * This cleans up the created testingDB & invalidDB from the tests. 
  //  */
  // @AfterAll
  // public static void cleanupTest(){

  // }

  /**
   * We are testing if we can get the expected string returned by the index, home
   * and/or the root route. 
   * 
   */
  @Test
  public void indexRouteTest() {
    String expectedResult = "Welcome, in order to make an API call direct your browser or Postman to an endpoint "
        + "\n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";
    assertEquals(expectedResult, testRouteController.index());
  }
  
  /**
   * The test department instance used for testing and the courses held within the Department.
   */
  public static RouteController testRouteController;
}

