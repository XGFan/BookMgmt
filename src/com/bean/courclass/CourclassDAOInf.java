package com.bean.courclass;

import java.util.List;

public interface CourclassDAOInf {

    public List findByCorSem(String idcor, String semester);
}
