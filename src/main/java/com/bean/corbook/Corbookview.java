package com.bean.corbook;

/**
 * Corbookview entity. @author MyEclipse Persistence Tools
 */
/*专业课程书本安排表*/
public class Corbookview implements java.io.Serializable {

    private int id;
    /*学院名称*/
    private String col;
    /*专业名称*/
    private String major;
    /*课程id*/
    private String idcor;
    /*课程名称*/
    private String corname;
    /*保存班级处在某一学期*/
    private String semester;
    /*TODO*/
    private String idcorsem;
    /*书本id*/
    private String idbk;
    /*书本名称*/
    private String bkname;
    /*作者*/
    private String author;
    /*书本版本*/
    private int edition;
    /*ISBN号*/
    private String isbn;
    /*出版社*/
    private String publisher;

    public Corbookview() {
        super();
    }

    public Corbookview(int id, String col, String major, String idcor,
                       String corname, String semester, String idcorsem, String idbk,
                       String bkname, String author, int edition, String isbn,
                       String publisher) {
        super();
        this.id = id;
        this.col = col;
        this.major = major;
        this.idcor = idcor;
        this.corname = corname;
        this.semester = semester;
        this.idcorsem = idcorsem;
        this.idbk = idbk;
        this.bkname = bkname;
        this.author = author;
        this.edition = edition;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getIdcorsem() {
        return idcorsem;
    }

    public void setIdcorsem(String idcorsem) {
        this.idcorsem = idcorsem;
    }

    public String getIdbk() {
        return idbk;
    }

    public void setIdbk(String idbk) {
        this.idbk = idbk;
    }

    public String getBkname() {
        return bkname;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Corbookview [id=" + id + ", col=" + col + ", major=" + major
                + ", idcor=" + idcor + ", corname=" + corname + ", semester="
                + semester + ", idcorsem=" + idcorsem + ", idbk=" + idbk
                + ", bkname=" + bkname + ", author=" + author + ", edition="
                + edition + ", isbn=" + isbn + ", publisher=" + publisher + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((bkname == null) ? 0 : bkname.hashCode());
        result = prime * result + ((col == null) ? 0 : col.hashCode());
        result = prime * result + ((corname == null) ? 0 : corname.hashCode());
        result = prime * result + edition;
        result = prime * result + id;
        result = prime * result + ((idbk == null) ? 0 : idbk.hashCode());
        result = prime * result + ((idcor == null) ? 0 : idcor.hashCode());
        result = prime * result
                + ((idcorsem == null) ? 0 : idcorsem.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((major == null) ? 0 : major.hashCode());
        result = prime * result
                + ((publisher == null) ? 0 : publisher.hashCode());
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
        Corbookview other = (Corbookview) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (bkname == null) {
            if (other.bkname != null)
                return false;
        } else if (!bkname.equals(other.bkname))
            return false;
        if (col == null) {
            if (other.col != null)
                return false;
        } else if (!col.equals(other.col))
            return false;
        if (corname == null) {
            if (other.corname != null)
                return false;
        } else if (!corname.equals(other.corname))
            return false;
        if (edition != other.edition)
            return false;
        if (id != other.id)
            return false;
        if (idbk == null) {
            if (other.idbk != null)
                return false;
        } else if (!idbk.equals(other.idbk))
            return false;
        if (idcor == null) {
            if (other.idcor != null)
                return false;
        } else if (!idcor.equals(other.idcor))
            return false;
        if (idcorsem == null) {
            if (other.idcorsem != null)
                return false;
        } else if (!idcorsem.equals(other.idcorsem))
            return false;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (major == null) {
            if (other.major != null)
                return false;
        } else if (!major.equals(other.major))
            return false;
        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;
        if (semester == null) {
            if (other.semester != null)
                return false;
        } else if (!semester.equals(other.semester))
            return false;
        return true;
    }

}