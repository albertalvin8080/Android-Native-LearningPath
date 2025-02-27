package org.albert.pluralsight01

class DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initCourses()
    }

    private fun initCourses()
    {
        var course = CourseInfo("c#_development", "How to Develop apps using C#")
        courses.set(course.courseId, course)

        course = CourseInfo("java", "Java Fundamentals Course")
        courses[course.courseId] = course

        course = CourseInfo("c++", "C++ Fundamentals Course")
        courses[course.courseId] = course

        course = CourseInfo("sql", "SQL Fundamentals Course")
        courses[course.courseId] = course
    }
}