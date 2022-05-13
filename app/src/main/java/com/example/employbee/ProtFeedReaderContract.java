package com.example.employbee;

import android.provider.BaseColumns;

public final class ProtFeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ProtFeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "protocols";
        public static final String COLUMN_NAME_TITLE1 = "prot_name";
        public static final String COLUMN_NAME_TITLE2 = "prot_text";

    }


}