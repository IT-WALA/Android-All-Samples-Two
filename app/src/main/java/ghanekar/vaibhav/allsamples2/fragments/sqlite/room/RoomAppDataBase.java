package ghanekar.vaibhav.allsamples2.fragments.sqlite.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;

import ghanekar.vaibhav.allsamples2.fragments.sqlite.room.roomcipher.SafeHelperFactory;

@Database(entities = {SqlitePojo.class}, version = 2, exportSchema = false)
@TypeConverters({StringConverters.class})
public abstract class RoomAppDataBase extends RoomDatabase {

    private static RoomAppDataBase instance;
    private static Editable password;

    public abstract RoomDao roomDao();

    public static RoomAppDataBase getInstance(Context context) {
        if (null == instance) {
            password = new SpannableStringBuilder("password");
            SafeHelperFactory factory = SafeHelperFactory.fromUser(password);

            instance = Room.databaseBuilder(context, RoomAppDataBase.class, "RoomDb")
                    .addMigrations(RoomMigration.MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .openHelperFactory(factory)
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
