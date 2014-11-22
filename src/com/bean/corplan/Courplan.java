package com.bean.corplan;

import com.bean.course.Course;

/**
 * Courplan entity. @author MyEclipse Persistence Tools
 */

public class Courplan implements java.io.Serializable {

    // Fields

    private CourplanId id;
    private String idcorsem;
    private String idcor;
    private String corname;
    private String col;
    private String major;
    private String semester;
    private Course course;


    // Constructors

    /**
     * default constructor
     */
    public Courplan() {
    }

    /**
     * full constructor
     */
    public Courplan(CourplanId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Property accessors

    public CourplanId getId() {
        return this.id;
    }

    public void setId(CourplanId id) {
        this.id = id;
    }

    public String getIdcorsem() {
        return idcorsem;
    }

    public void setIdcorsem(String idcorsem) {
        this.idcorsem = idcorsem;
    }

    public String getIdcor() {
        return idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    public String getCorname() {
        return corname;
    }

    public void setCorname(String corname) {
        this.corname = corname;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    /**
     * CourplanId entity. @author MyEclipse Persistence Tools
     */

    public static class CourplanId implements java.io.Serializable {

        // Fields

        private String idcorsem;
        private String idcor;
        private String corname;
        private String col;
        private String major;
        private String semester;

        // Constructors

        /**
         * default constructor
         */
        public CourplanId() {
        }

        /**
         * minimal constructor
         */
        public CourplanId(String idcorsem, String idcor, String col, String major,
                          String semester) {
            this.idcorsem = idcorsem;
            this.idcor = idcor;
            this.col = col;
            this.major = major;
            this.semester = semester;
        }

        /**
         * full constructor
         */
        public CourplanId(String idcorsem, String idcor, String corname,
                          String col, String major, String semester) {
            this.idcorsem = idcorsem;
            this.idcor = idcor;
            this.corname = corname;
            this.col = col;
            this.major = major;
            this.semester = semester;
        }

        // Property accessors

        String getIdcorsem() {
            return this.idcorsem;
        }

        public void setIdcorsem(String idcorsem) {
            this.idcorsem = idcorsem;
        }

        String getIdcor() {
            return this.idcor;
        }

        public void setIdcor(String idcor) {
            this.idcor = idcor;
        }

        String getCorname() {
            return this.corname;
        }

        public void setCorname(String corname) {
            this.corname = corname;
        }

        String getCol() {
            return this.col;
        }

        public void setCol(String col) {
            this.col = col;
        }

        String getMajor() {
            return this.major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        String getSemester() {
            return this.semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public boolean equals(Object other) {
            if ((this == other))
                return true;
            if ((other == null))
                return false;
            if (!(other instanceof CourplanId))
                return false;
            CourplanId castOther = (CourplanId) other;

            return ((this.getIdcorsem() == castOther.getIdcorsem()) || (this
                    .getIdcorsem() != null
                    && castOther.getIdcorsem() != null && this.getIdcorsem()
                    .equals(castOther.getIdcorsem())))
                    && ((this.getIdcor() == castOther.getIdcor()) || (this
                    .getIdcor() != null
                    && castOther.getIdcor() != null && this.getIdcor()
                    .equals(castOther.getIdcor())))
                    && ((this.getCorname() == castOther.getCorname()) || (this
                    .getCorname() != null
                    && castOther.getCorname() != null && this.getCorname()
                    .equals(castOther.getCorname())))
                    && ((this.getCol() == castOther.getCol()) || (this.getCol() != null
                    && castOther.getCol() != null && this.getCol().equals(
                    castOther.getCol())))
                    && ((this.getMajor() == castOther.getMajor()) || (this
                    .getMajor() != null
                    && castOther.getMajor() != null && this.getMajor()
                    .equals(castOther.getMajor())))
                    && ((this.getSemester() == castOther.getSemester()) || (this
                    .getSemester() != null
                    && castOther.getSemester() != null && this
                    .getSemester().equals(castOther.getSemester())));
        }

        public int hashCode() {
            int result = 17;

            result = 37 * result
                    + (getIdcorsem() == null ? 0 : this.getIdcorsem().hashCode());
            result = 37 * result
                    + (getIdcor() == null ? 0 : this.getIdcor().hashCode());
            result = 37 * result
                    + (getCorname() == null ? 0 : this.getCorname().hashCode());
            result = 37 * result
                    + (getCol() == null ? 0 : this.getCol().hashCode());
            result = 37 * result
                    + (getMajor() == null ? 0 : this.getMajor().hashCode());
            result = 37 * result
                    + (getSemester() == null ? 0 : this.getSemester().hashCode());
            return result;
        }

    }
}