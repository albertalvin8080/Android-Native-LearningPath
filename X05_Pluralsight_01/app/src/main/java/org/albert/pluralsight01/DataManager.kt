package org.albert.pluralsight01

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initCourses()
        initNotes()
    }

    private fun initNotes()
    {
        for (course in courses.values) {
            notes.add(NoteInfo(course, "Detestatio Sacrorum", "Lorem ipsum dolor amet scir sciato"))
            notes.add(NoteInfo(course, "Advanced Topics", "Exploring advanced concepts in ${course.title}"))
            notes.add(NoteInfo(course, "Practical Applications", "Applying ${course.title} in real-world scenarios"))
        }
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