package InterfaceDao;

import Models.Course;
import Models.CourseType;

import java.util.List;

public interface CourseTypeDao {

    List<CourseType> getAllCourseType();
    void createCoursDao(CourseType courseType);
    CourseType readCourseTypeByName(String name);
    CourseType readCourseTypeById(Long Id);
    void updateCourseTypeDao(CourseType courseType);
    void deleteCourseTypeDao(CourseType courseType);
}
