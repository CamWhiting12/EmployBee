package com.example.employbee;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TITLE1 = "task";
        public static final String COLUMN_NAME_TITLE2 = "shift";
        public static final String COLUMN_NAME_TITLE3 = "position";
        public static final String COLUMN_NAME_TITLE4 = "done";
    }


}
