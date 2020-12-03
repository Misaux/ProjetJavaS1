package InterfaceDao;

import models.Course;

import java.util.List;

public interface CoursDao {
    List<Course> getAllCourse();
    Course readCourseByName(String name);
}
