package com.bean.course;

import com.bean.college.College;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */
@XmlRootElement
@JsonIgnoreProperties(value = {"idcm", "hibernateLazyInitializer", "semester"})
public class Course implements java.io.Serializable {

    // Fields

    private String idcor;
    private String idcm;
    @JsonUnwrapped
    private College college;
    private String corname;
    private String semester;
    @JsonIgnore
    private Set corplans = new HashSet(0);
    @JsonIgnore
    private Set coursebks = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Course() {
    }

    /**
     * minimal constructor
     */
    public Course(String idcor) {
        this.idcor = idcor;
    }

    /**
     * full constructor
     */
    public Course(String idcor, College college, String corname,
                  String semester, Set corplans, Set coursebks) {
        this.idcor = idcor;
        this.college = college;
        this.corname = corname;
        this.semester = semester;
        this.corplans = corplans;
        this.coursebks = coursebks;
    }

    public Course(String idcor, College college, String corname,
                  String semester) {
        this.idcor = idcor;
        this.college = college;
        this.corname = corname;
        this.semester = semester;
    }

    // Property accessors

    public String getIdcor() {
        return this.idcor;
    }

    public void setIdcor(String idcor) {
        this.idcor = idcor;
    }

    public College getCollege() {
        return this.college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public String getCorname() {
        return this.corname;
    }

    public void setCorname(String corname) {
        this.corname = corname;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Set getCorplans() {
        return this.corplans;
    }

    public void setCorplans(Set corplans) {
        this.corplans = corplans;
    }

    public Set getCoursebks() {
        return this.coursebks;
    }

    public void setCoursebks(Set coursebks) {
        this.coursebks = coursebks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((college == null) ? 0 : college.hashCode());
        result = prime * result + ((corname == null) ? 0 : corname.hashCode());
        result = prime * result
                + ((corplans == null) ? 0 : corplans.hashCode());
        result = prime * result
                + ((coursebks == null) ? 0 : coursebks.hashCode());
        result = prime * result + ((idcor == null) ? 0 : idcor.hashCode());
        result = prime * result
                + ((semester == null) ? 0 : semester.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (college == null) {
            if (other.college != null)
                return false;
        } else if (!college.equals(other.college))
            return false;
        if (corname == null) {
            if (other.corname != null)
                return false;
        } else if (!corname.equals(other.corname))
            return false;
        if (corplans == null) {
            if (other.corplans != null)
                return false;
        } else if (!corplans.equals(other.corplans))
            return false;
        if (coursebks == null) {
            if (other.coursebks != null)
                return false;
        } else if (!coursebks.equals(other.coursebks))
            return false;
        if (idcor == null) {
            if (other.idcor != null)
                return false;
        } else if (!idcor.equals(other.idcor))
            return false;
        if (semester == null) {
            if (other.semester != null)
                return false;
        } else if (!semester.equals(other.semester))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Course [idcor=" + idcor + ", college=" + college + ", corname="
                + corname + ", semester=" + semester + ", corplans=" + corplans
                + ", coursebks=" + coursebks + "]";
    }

	public String getIdcm() {
		return idcm;
	}

	public void setIdcm(String idcm) {
		this.idcm = idcm;
	}

}