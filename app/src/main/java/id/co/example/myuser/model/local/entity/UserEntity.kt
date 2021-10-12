package id.co.example.myuser.model.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUser")
    var idUser: Int? = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "company")
    var company: String? = null,

    @ColumnInfo(name = "photo")
    var photo: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
):Parcelable

