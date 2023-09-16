package com.example.todo.obj

import android.os.Parcel
import android.os.Parcelable

public class Todo(
    /**
     * 요약
     */
    val summary: String? = "",

    /**
     * 날짜
     */
    val date: String? = "",

    /**
     * 장소
     */
    val place: String? = "",
    val content: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(summary)
        p0.writeString(date)
        p0.writeString(place)
        p0.writeString(content)
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }

}