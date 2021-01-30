package com.xadmin.LA.LILO;

public class Tea {
	
	protected int id;
    protected String classname;
    protected String subjectname;
    protected String teachername;
    
    
    public Tea() {
    }
 
    public Tea(int id) {
        this.id = id;
    }
 
    public Tea(int id, String classname, String subjectname, String teachername) {
        this(classname, subjectname, teachername);
        this.id = id;
    }
     
    public Tea(String classname, String subjectname, String teachername) {
        this.classname = classname;
        this.subjectname = subjectname;
        this.teachername = teachername;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
    
    
    
    
    
    
    

}
