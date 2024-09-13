package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This is just testing if we can create a course object and return expected results from each
 * method.
 */
@SpringBootTest
@ContextConfiguration
public class ProjectUnitTests {
  
  @BeforeAll
  public static void setupCourseForTesting() {
    
  }
  
  /**
   * This cleans up the created testingDB & invalidDB from the tests. 
   */
  @AfterAll
  public static void cleanupTest() {

  }
   
  /**
   * The test course instance used for testing.
   */
  public static Course testCourse;
}

