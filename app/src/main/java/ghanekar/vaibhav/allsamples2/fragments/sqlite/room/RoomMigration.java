package ghanekar.vaibhav.allsamples2.fragments.sqlite.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;

public class RoomMigration {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE RoomTable ADD COLUMN age INTEGER NOT NULL DEFAULT 0");
        }
    };
}
