package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpRequest;
import java.util.HashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * This is just testing if we can read our database from the ./data.txt file
 * and extract departments & courses from the database.
 */
@SpringBootTest
@ContextConfiguration
public class RouteUnitTests {
  
  /**
   * We are creating a routeController instance to test the routes.
   */
  @BeforeAll
  public static void setupTestRouteController() {

    testRouteController = new RouteController();
    
  }
  
  /**
   * Instantiating our test department that we'll be working with throughout testing.
   */
  @BeforeAll
  public static void setupDepartmentForTesting() {
    Course coms4156 = new Course("Gail Kaiser", "501 NWC", "11:40-12:55", 120);
    coms4156.setEnrolledStudentCount(109);
    courses = new HashMap<>();
    courses.put("4156", coms4156);
    testDept = new Department("COMS", courses, "Luca Carloni", 2700);
  }

  /**
   * We are testing if we can get the expected string returned by the index, home
   * and/or the root route. 
   */
  @Test
  public void indexRouteTest() {
    String expectedResult = "Welcome, in order to make an API call direct your browser or"
        + " Postman to an endpoint "
        + "\n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";

    assertEquals(expectedResult, testRouteController.index());
  }

  /**
   * We are testing if we can receive Department Not Found when we try to retrieve a department
   * that doesn't exist within data.txt
   */
  @Test
  public void retrieveInvalidDepartmentTest() {
    String expectedResult = "Department Not Found";
    ResponseEntity<?> response = testRouteController.retrieveDepartment("cheese");
    assertEquals(expectedResult, response.getBody());
  }

  /**
   * We are testing if we can receive an HTTP 200 response when we try to retrieve a valid 
   * department from our database. 
   */
  @Test
  public void retrieveValidDepartmentTest() {
    String expectedResult = "200 OK";
    ResponseEntity<?> response = testRouteController.retrieveDepartment("COMS");
    HttpStatusCode responseStatus = response.getStatusCode();
    assertEquals(expectedResult, responseStatus.toString());
  }

  /**
   * We are testing if we can generate an exception by inputting a null pointer
   * within the String deptCode argument passed into retrieveDepartment. 
   */
  @Test
  public void retrieveDepartmentInvalidInputTest() {
    String expectedResult = "An Error has occurred";
    ResponseEntity<?> response = testRouteController.retrieveDepartment(null);
    assertEquals(expectedResult, response.getBody());
  }

  /**
   * We are testing to see if we can input a valid department and course within said department
   * to retrieve a course from the database. We will ensure our course was retrieved appropriately
   * by checking the course's toString method.
   */
  @Test
  public void retrieveValidCourseTest() {
    String expectedResult = "\nInstructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25";
    ResponseEntity<?> response = testRouteController.retrieveCourse("COMS", 4156);
    assertEquals(expectedResult, response.getBody());
  }

  /**
   * We are testing to see if we can input a valid department and invalid course within said department
   * to retrieve an error message from the database stating that the course was not found. 
   */
  @Test
  public void retrieveInvalidCourseValidDepartmentTest() {
    String expectedResult = "Course Not Found";
    ResponseEntity<?> response = testRouteController.retrieveCourse("COMS", 4995);
    assertEquals(expectedResult, response.getBody());
  }

  /**
   * We are testing to see if we can input an invalid department but valid course within another 
   * department to retrieve an error message from the database stating that the department was not found. 
   */
  @Test
  public void retrieveValidCourseInvalidDepartmentTest() {
    String expectedResult = "Department Not Found";
    ResponseEntity<?> response = testRouteController.retrieveCourse("cheese", 4156);
    assertEquals(expectedResult, response.getBody());
  }

  /**
   * We are testing to see if we can cause an exception from within the retriveCourse method by
   * inputting a valid department but a null courseCode. 
   */
  @Test
  public void retrieveCourseInvalidInputTest() {
    String expectedResult = "An Error has occurred";
    ResponseEntity<?> response = testRouteController.retrieveCourse("COMS", null);
    assertEquals(expectedResult, response.getBody());
  }

  /**
   * We are testing to see if a valid course within a valid department is at capacity or not. 
   */
  @Test
  public void isCourseFullValidTest() {
    String expectedResult = "200 OK";
    ResponseEntity<?> response = testRouteController.retrieveCourse("IEOR", 3404);
    HttpStatusCode responseStatus = response.getStatusCode();
    assertEquals(expectedResult, responseStatus.toString());
  }
  
  /**
   * The test department instance used for testing and the courses held within the Department.
   * RouteController instance created for testing the route responses. 
   */
  public static RouteController testRouteController;
  public static Department testDept;
  public static HashMap<String, Course> courses;
}

