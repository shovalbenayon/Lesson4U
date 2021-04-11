package com.example.lesson4u;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class LessonObj implements Parcelable {
    private String teacherUID;
    private String studentUID = "";
    private String subject;
    private String level;
    private int price;
    private String date;
    private String time;
    private boolean isScheduled = false;

    public String getStudentUID() {
        return studentUID;
    }

    public void setStudentUID(String studentID) {
        this.studentUID = studentID;
    }


    public LessonObj() {
    }

    public LessonObj(String uid, String sub, String lev, int pr, String date, String time) {
        teacherUID = uid;
        subject = sub;
        level = lev;
        price = pr;
        this.date = date;
        this.time = time;
    }


    protected LessonObj(Parcel in) {
        teacherUID = in.readString();
        studentUID = in.readString(); // ???
        subject = in.readString();
        level = in.readString();
        price = in.readInt();
        date = in.readString();
        time = in.readString();
        isScheduled = false;
    }

    public static final Creator<LessonObj> CREATOR = new Creator<LessonObj>() {
        @Override
        public LessonObj createFromParcel(Parcel in) {
            return new LessonObj(in);
        }

        @Override
        public LessonObj[] newArray(int size) {
            return new LessonObj[size];
        }
    };

    public String getTeacherUID() {
        return teacherUID;
    }

    public void setTeacherUID(String teacherUID) {
        this.teacherUID = teacherUID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonObj lessonObj = (LessonObj) o;
        return price == lessonObj.price &&
                isScheduled == lessonObj.isScheduled &&
                teacherUID.equals(lessonObj.teacherUID) &&
                subject.equals(lessonObj.subject) &&
                level.equals(lessonObj.level) &&
                date.equals(lessonObj.date) &&
                time.equals(lessonObj.time);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teacherUID);
        dest.writeString(studentUID); // ???
        dest.writeString(subject);
        dest.writeString(level);
        dest.writeInt(price);
        dest.writeString(date);
        dest.writeString(time);
    }
}


