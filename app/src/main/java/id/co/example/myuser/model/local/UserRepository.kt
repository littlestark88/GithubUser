package id.co.example.myuser.model.local

import android.app.Application
import androidx.lifecycle.LiveData
import id.co.example.myuser.model.local.dao.UserDao
import id.co.example.myuser.model.local.entity.UserEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val userDao: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserDatabase.getDatabase(application)
        userDao = db.userDao()
    }

    fun getAllUser(): LiveData<List<UserEntity>> = userDao.getUser()

    fun insertUser(userEntity: UserEntity) =
        executorService.execute { userDao.insertUser(userEntity) }

    fun deleteUser(userEntity: UserEntity) =
        executorService.execute { userDao.deleteUser(userEntity) }

    fun updateUser(userEntity: UserEntity) =
        executorService.execute { userDao.updateUser(userEntity) }
}