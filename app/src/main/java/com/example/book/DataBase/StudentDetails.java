package com.example.book.DataBase;

import android.provider.BaseColumns;

public class StudentDetails {

    private StudentDetails() {

    }

        public static class Student implements BaseColumns {
            public static final String TABLE_NAME = "StudentInfo";
            public static final String COLUMN_1 = "student_name";
            public static final String COLUMN_2 = "address";
            public static final String COLUMN_3 = "contact_number";
            public static final String COLUMN_4 = "interested_areas";

    }
}
