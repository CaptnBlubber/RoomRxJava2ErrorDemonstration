package cpt.blubber.example.roomrxjava2sample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by rueggeberg on 12.11.17.
 */

@Dao
public interface ThingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ThingEntity thing);

    @Query("SELECT * from things WHERE [id] = :id")
    Single<ThingEntity> getThingByIdSingle(int id);

    @Query("SELECT * from things WHERE [id] = :id")
    Maybe<ThingEntity> getThingByIdMaybe(int id);

    @Query("SELECT * from things")
    Single<List<ThingEntity>> getThingsSingle();

    @Query("SELECT * from things")
    Maybe<List<ThingEntity>> getThingsMaybe();
}
