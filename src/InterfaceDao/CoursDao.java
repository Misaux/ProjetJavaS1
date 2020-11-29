package InterfaceDao;

import Models.Course;
import Models.CourseType;

import java.util.List;

public interface CoursDao {
    List<Course> getAllCourse();
    Course readCourseByName(String name);
}
