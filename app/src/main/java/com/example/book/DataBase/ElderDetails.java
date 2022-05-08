package com.example.book.DataBase;

import android.provider.BaseColumns;

public final class ElderDetails {

    private ElderDetails() {}

    public static class Elder implements BaseColumns {
        public static final String TABLE_NAME = "ElderInfo";
        public static final String COLUMN_1 = "customer_name";
        public static final String COLUMN_2 = "address";
        public static final String COLUMN_3 = "contact_number";
        public static final String COLUMN_4 = "customer_designation";
        public static final String COLUMN_5 = "interested_area";
    }
}
