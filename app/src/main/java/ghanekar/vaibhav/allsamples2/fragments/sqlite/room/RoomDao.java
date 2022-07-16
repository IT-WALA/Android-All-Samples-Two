package ghanekar.vaibhav.allsamples2.fragments.sqlite.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(SqlitePojo pojo);

    @Query("select * from RoomTable")
    List<SqlitePojo> getAllData();

    @Query("select * from RoomTable where id=:id")
    SqlitePojo getDataById(int id);

    @Query("select count(*) from RoomTable")
    int getDataCount();
}
