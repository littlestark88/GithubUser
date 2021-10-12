package id.co.example.myuser.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.co.example.myuser.model.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser (userEntity: UserEntity)

    @Update
    fun updateUser (userEntity: UserEntity)

    @Delete
    fun deleteUser (userEntity: UserEntity)

    @Query("SELECT * from userentity ORDER BY idUser ASC")
    fun getUser(): LiveData<List<UserEntity>>
}